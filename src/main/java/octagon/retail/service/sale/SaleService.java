package octagon.retail.service.sale;

import octagon.retail.entity.SaleItems;
import octagon.retail.entity.Sales;
import octagon.retail.model.sale.SaleModel;
import octagon.retail.repository.sale.SaleItemRepository;
import octagon.retail.repository.sale.SaleRepository;
import octagon.retail.response.ResponseModel;
import octagon.retail.utils.SaleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    SaleItemRepository saleItemRepository;

    public ResponseEntity<ResponseModel<Sales>> saveSale(SaleModel model) {
        var sale = saleRepository.findById(model.getId()).orElse(null);
        var convertedSale = SaleModel.convert(sale, model, SaleType.COMPLETE);
        var saved = saleRepository.save(convertedSale);
        var items = model.getStock().stream()
                .peek(i -> i.setSaleId(saved.getId()))
                .collect(Collectors.toList());
        saleItemRepository.saveAll(items);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saved));
    }

    public ResponseEntity<ResponseModel<Sales>> saveTemporary(SaleModel model) {
        var sale = SaleModel.convert(null, model, SaleType.TEMP);
        var saved = saleRepository.save(sale);
        var items = model.getStock().stream()
                .peek(i -> i.setSaleId(saved.getId()))
                .collect(Collectors.toList());
        saleItemRepository.saveAll(items);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saved));
    }

    public ResponseEntity<ResponseModel<List<SaleModel>>> getTempSales() {
        List<SaleModel> models = new ArrayList<>();
        var sales = saleRepository.findByType(SaleType.TEMP);
        if (sales.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
        for (var sale : sales) {
            var model = new SaleModel();
            model.setBranchId(sale.getBranchId());

            model.setId(sale.getId());
            model.setIsPaid(sale.getIsPaid());
            model.setPaidTotalAmount(sale.getTotalPaidAmount());
            model.setTotalAmount(sale.getTotalAmount());
            model.setTotalQty(sale.getTotalQty());
            var saleItems = saleItemRepository.findAllBySaleId(sale.getId());
            model.setStock(saleItems);
            models.add(model);
        }

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, models));
    }

    public ResponseEntity<ResponseModel<SaleModel>> initSale() {
        SaleModel resp = null;
        List<SaleItems> respSaleItems = new ArrayList<>();
        var sales = saleRepository.findByType(SaleType.INIT);
        if (!sales.isEmpty()) {
            for (var sale : sales) {
                respSaleItems = saleItemRepository.findAllById(sale.getId());
                if (respSaleItems.isEmpty()) {
                    resp = new SaleModel();
                    resp.setBranchId(sale.getBranchId());

                    resp.setIsPaid(sale.getIsPaid());
                    resp.setPaidTotalAmount(sale.getTotalPaidAmount());
                    resp.setTotalAmount(sale.getTotalAmount());
                    resp.setTotalQty(sale.getTotalQty());
                    resp.setId(sale.getId());
                    return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                            resp));
                } else {
                    resp = new SaleModel();
                    resp.setBranchId(sale.getBranchId());

                    resp.setIsPaid(sale.getIsPaid());
                    resp.setPaidTotalAmount(sale.getTotalPaidAmount());
                    resp.setStock(respSaleItems);
                    resp.setTotalAmount(sale.getTotalAmount());
                    resp.setTotalQty(resp.getTotalQty());
                    resp.setId(sale.getId());
                    return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                            resp));
                }
            }
        } else {
            var newSale = new Sales();
            newSale.setType(SaleType.INIT);
            var saved = saleRepository.save(newSale);
            resp = new SaleModel();
            resp.setId(saved.getId());
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    resp));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", true,
                null));
    }

    public ResponseEntity<ResponseModel<Sales>> updateSale(SaleModel model) {
        Sales sales = saleRepository.findById(model.getId()).orElse(null);

        if (sales != null) {
            var updatedSale = SaleModel.convert(sales, model, SaleType.COMPLETE);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, updatedSale));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Sales>> updateQtyAmountSale(Long id, Sales update) {
        Sales sales = saleRepository.findById(id).orElse(null);
        if (sales != null) {
            sales.setTotalQty(sales.getTotalQty().add(update.getTotalQty()));
            sales.setTotalAmount(sales.getTotalAmount().add(update.getTotalAmount()));
            saleRepository.save(sales);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sales));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<Sales>>> getMany(String startDate, String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(startDate, formatter);
            LocalDateTime end = LocalDateTime.parse(endDate, formatter);
            List<Sales> sales = saleRepository.getManyByDate(start, end);
            if (sales.isEmpty()) {
                return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалт олдсонгүй", false,
                        null));

            }
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    sales));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false, null));
        }

    }

    public ResponseEntity<ResponseModel<Sales>> getOne(Long Id) {
        Sales sale = saleRepository.findById(Id).orElse(null);
        if (sale != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
        } else {
            return ResponseEntity
                    .ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, null));
        }
    }

    public ResponseEntity<ResponseModel<Sales>> isPaid(Long id, Sales sales) {
        Sales isPaid = saleRepository.findById(id).orElse(null);

        if (isPaid != null) {
            isPaid.setIsPaid(true);
            isPaid.setTotalPaidAmount(sales.getTotalPaidAmount());
            saleRepository.save(isPaid);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, isPaid));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    // public ResponseEntity<ResponseModel<Sales>> deleteSale(Long saleId) {

    // Sales deleteSale = saleRepository.findById(saleId).orElse(null);

    // if (deleteSale != null) {

    // var result = saleItemService.deleteAllBySaleId(saleId);
    // if (result == null) {
    // return ResponseEntity
    // .ok(new ResponseModel<>("500", "Устгахад алдаа гарлаа ахин оролдоно уу",
    // false, null));
    // }
    // saleRepository.save(deleteSale);
    // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
    // deleteSale));
    // }
    // return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл
    // олдсонгүй", false, null));
    // }

    public ResponseEntity<ResponseModel<Object>> getDashboardData(Date date) {

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("income", saleRepository.getTotalAmountByDate(date));
        data.put("profit", saleRepository.getProfitByDate(date));
        data.put("quantity", saleRepository.getTotalQuantityByDate(date));

        if (data.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
        }
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
    }

    public ResponseEntity<ResponseModel<Object>> getDailyIncome (Date date) {

        Map <String, Object> data = new HashMap<String, Object>();

        Object obj = saleRepository.getTotalAmountByDate(date);

        data.put("daily_income" , saleRepository.getTotalAmountByDate(date));

        if(obj != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Өдрийн борлуулалтын мэдээлэл олдсонгүй", false, null));
    }


    public ResponseEntity<ResponseModel<Object>> getAllSales () {

        List<Sales> data = saleRepository.findAll();

        if (data != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
    }
}

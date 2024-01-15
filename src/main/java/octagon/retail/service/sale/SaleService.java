package octagon.retail.service.sale;

import octagon.retail.entity.SaleItems;
import octagon.retail.entity.Sales;
import octagon.retail.model.sale.SaleModel;
import octagon.retail.repository.sale.SaleItemRepository;
import octagon.retail.repository.sale.SaleRepository;
import octagon.retail.response.ResponseModel;
import octagon.retail.utils.SaleType;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemService saleItemService;

    @Autowired
    SaleItemRepository saleItemRepository;

    public ResponseEntity<ResponseModel<Sales>> saveSale(Sales sale) {
        saleRepository.save(sale);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
    }

    // public ResponseEntity<ResponseModel<Sales>> addSaleItem(SaleItems saleItem) {
    // var sale = saleRepository.findById(saleItem.getSaleId()).orElse(null);
    // if (sale == null) {
    // sale = new Sales();
    // sale.setType(SaleType.INIT);

    // var savedSale = saleRepository.save(sale);
    // var modifiedSaleItem = saleItem;
    // modifiedSaleItem.setSaleId(savedSale.getId());
    // saleItemRepository.save(modifiedSaleItem);
    // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
    // savedSale));
    // }

    // }

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
                    resp.setDate(sale.getDate());
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
                    resp.setDate(sale.getDate());
                    resp.setIsPaid(sale.getIsPaid());
                    resp.setPaidTotalAmount(sale.getTotalPaidAmount());
                    resp.setStock(respSaleItems);
                    resp.setTotalAmount(sale.getTotalAmount());
                    resp.setTotalQty(resp.getTotalQty());
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

    public ResponseEntity<ResponseModel<Sales>> updateSale(Long id, Sales update) {
        Sales sales = saleRepository.findById(id).orElse(null);
        if (sales != null) {
            sales.setTotalQty(update.getTotalQty());
            sales.setTotalAmount(update.getTotalAmount());
            saleRepository.save(sales);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sales));
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date formatStartDate = dateFormat.parse(startDate);
            Date formatEndDate = dateFormat.parse(endDate);
            List<Sales> sales = saleRepository.getManyByDate(formatStartDate, formatEndDate);
            if (!sales.isEmpty()) {
                return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sales));
            } else {
                return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
            }
        } catch (ParseException e) {
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

    public ResponseEntity<ResponseModel<Object>> getAllSales() {

        List<Sales> data = saleRepository.findAll();

        if (data != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
    }
}

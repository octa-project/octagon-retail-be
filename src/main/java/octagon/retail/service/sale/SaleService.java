package octagon.retail.service.sale;

import octagon.retail.entity.sale.Sales;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemService saleItemService;
    public ResponseEntity<ResponseModel<Sales>> saveSale(Sales sale) {
        saleRepository.save(sale);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
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
    public ResponseEntity<ResponseModel<List<Sales>>> getMany(Date startDate,Date endDate) {
        List<Sales> sales = saleRepository.getMany(startDate,endDate);
        if (!sales.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sales));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }
    public ResponseEntity<ResponseModel<Sales>> getOne(Long Id) {
        Sales sale = saleRepository.findById(Id).orElse(null);
        if (sale != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, null));
        }
    }
    public ResponseEntity<ResponseModel<Sales>> isPaid(Long saleId, Sales sales) {
        Sales isPaid = saleRepository.findById(saleId).orElse(null);

        if (isPaid != null) {
            isPaid.setIsPaid(true);
            isPaid.setPaidTotalAmount(sales.getPaidTotalAmount());
            saleRepository.save(isPaid);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, isPaid));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
    public ResponseEntity<ResponseModel<Sales>> deleteSale(Long saleId) {

        Sales deleteSale = saleRepository.findById(saleId).orElse(null);

        if (deleteSale != null) {

            var result = saleItemService.deleteAllBySaleId(saleId);
            if (result == null) {
                return ResponseEntity.ok(new ResponseModel<>("500", "Устгахад алдаа гарлаа ахин оролдоно уу", false, null));
            }
            deleteSale.setIsDeleted(true);
            saleRepository.save(deleteSale);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, deleteSale));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Object>> getDashboardData (Date date) {

        Map <String, Object> data = new HashMap<String, Object>();

        data.put("income" , saleRepository.getTotalAmountByDate(date));
        data.put("profit" , saleRepository.getTotalAmountByDate(date));
        data.put("quantity" , saleRepository.getTotalAmountByDate(date));

        if(data != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
        }
       return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
    }



}

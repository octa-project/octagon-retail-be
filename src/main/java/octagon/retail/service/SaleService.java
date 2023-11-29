package octagon.retail.service;

import octagon.retail.entity.Sales;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Printable;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class SaleService {

    @Autowired
    private ISaleRepository saleRepository;

    public ResponseEntity<ResponseModel<Sales>> saveSale(Sales sale) {
        saleRepository.save(sale);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
    }

    public ResponseEntity<ResponseModel<Sales>> updateSale(Sales updateSale) {
        Sales saleIsExist = saleRepository.findById(updateSale.getId()).orElse(null);
        if (saleIsExist != null) {

            saleIsExist.setDate(updateSale.getDate());
            saleIsExist.setTotalQty(updateSale.getTotalQty());
            saleIsExist.setTotalAmount(updateSale.getTotalAmount());
            saleIsExist.setPaidTotalAmount(updateSale.getPaidTotalAmount());
            saleIsExist.setIsPaid(updateSale.getIsPaid());
            saleIsExist.setBranchId(updateSale.getBranchId());
            saleRepository.save(saleIsExist);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saleIsExist));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }


//
//    public ResponseEntity<ResponseModel<Sales>> deleteSaleById(Long saleId) {
//        Sales sale = saleRepository.findById(saleId).orElse(null);
//
//        if (sale != null) {
//            // Manually delete associated sale items
//            var result = saleItemService.deleteAllBySaleId(saleId);
//            if (result == null) {
//                return ResponseEntity.ok(new ResponseModel<>("500", "Устгахад алдаа гарлаа ахин оролдоно уу", false, null));
//            }
//            sale.setDeleted(true);
//            saleRepository.deleteById(saleId);
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
//        } else {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалт олдсонгүй", false, null));
//        }
//    }
//
//    public ResponseEntity<ResponseModel<List<Sales>>> getSales() {
//        List<Sales> sales = saleRepository.findAll();
//        if (sales != null) {
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sales));
//        } else {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
//        }
//    }
//
//    public ResponseEntity<ResponseModel<Sales>> getSaleById(Long Id) {
//        Sales sale = saleRepository.findById(Id).orElse(null);
//        if (sale != null) {
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
//        } else {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, null));
//        }
//    }
//
//    public ResponseEntity<ResponseModel<Sales>> updateSaleIsPaid(Long saleId) {
//        Sales existingSale = saleRepository.findById(saleId).orElse(null);
//
//        if (existingSale != null) {
//
//            existingSale.setPaid(true);
//            saleRepository.save(existingSale);
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingSale));
//        }
//        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
//    }

    public ResponseEntity<ResponseModel<Sales>> deleteSale(Long saleId) {
        Sales sale = saleRepository.findById(saleId).orElse(null);

        if (sale != null) {
            // Manually delete associated sale items
//            var result = saleItemService.deleteAllBySaleId(saleId);
//            if (result == null) {
//                return ResponseEntity.ok(new ResponseModel<>("500", "Устгахад алдаа гарлаа дахин оролдоно уу", false, null));
//            }
            sale.setIsDeleted(true);
            saleRepository.deleteById(saleId);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалтын мэдээлэл олдсонгүй", false, null));
    }
}

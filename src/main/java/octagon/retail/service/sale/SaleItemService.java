package octagon.retail.service.sale;

import octagon.retail.entity.sale.SaleItems;
import octagon.retail.model.sale.SaleItemModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.sale.SaleItemRepository;
import octagon.retail.repository.sale.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    SaleRepository saleRepository;
    @Autowired
    IItemCodeRepository itemCodeRepository;

    public ResponseEntity<ResponseModel<SaleItems>> addItem(SaleItemModel req) {
        var sale = saleRepository.findById(req.getSaleId()).orElse(null);
        if (sale == null) {
            return ResponseEntity.ok(new ResponseModel<>("500",
                    "Амжилтгүй борлуулалт олдсонгүй", false, null));
        }
        var saleItem = sale.getStocks().stream().filter(i -> i.getId() == req.getId()).findFirst().orElse(null);

        if (saleItem == null) {
            var item = itemCodeRepository.findById(req.getItemCodeId()).orElse(null);
            var newSaleItem = new SaleItems();
            newSaleItem.setBranchId(req.getBranchId());
            newSaleItem.setItemCode(item);
            newSaleItem.setQty(req.getQty());
            newSaleItem.setSale(sale);
            newSaleItem.setTotalSalePrice(req.getTotalSalePrice());
            newSaleItem.setUnitSalePrice(req.getUnitSalePrice());
            sale.getStocks().add(newSaleItem);
            saleRepository.save(sale);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай мөр нэмлээ",
                    true, saleItem));
        }
        var item = itemCodeRepository.findById(req.getItemCodeId()).orElse(null);
        saleItem.setItemCode(item);
        sale.getStocks().add(saleItem);
        BigDecimal qty = saleItem.getQty().add(saleItem.getQty());
        BigDecimal amount = saleItem.getUnitSalePrice();
        BigDecimal totalAmount = qty.multiply(amount);

        saleItem.setQty(qty);
        saleItem.setUnitSalePrice(amount);
        saleItem.setTotalSalePrice(totalAmount);
        saleRepository.save(sale);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгалагдлаа", true, saleItem));
    }

    public ResponseEntity<ResponseModel<SaleItems>> updateItem(Long id, SaleItems updateItem) {
        SaleItems items = saleItemRepository.findById(id).orElse(null);

        if (items != null) {
            BigDecimal qty = updateItem.getQty();
            BigDecimal amount = updateItem.getUnitSalePrice();
            BigDecimal totalAmount = qty.multiply(amount);

            items.setQty(qty);
            items.setUnitSalePrice(amount);
            items.setTotalSalePrice(totalAmount);
            saleItemRepository.save(items);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
        }

        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<SaleItems>>> deleteAllBySaleId(Long saleId) {
        List<SaleItems> items = saleItemRepository.getSaleById(saleId);

        if (items.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("500",
                    "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));

        }

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));

    }

    public ResponseEntity<ResponseModel<SaleItems>> deleteItem(Long itemId) {
        saleItemRepository.deleteById(itemId);
        SaleItems saleItem = saleItemRepository.findById(itemId).orElse(null);
        if (saleItem == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, saleItem));
    }

    public ResponseEntity<ResponseModel<List<SaleItems>>> manySaleId(Long saleId) {
        List<SaleItems> items = saleItemRepository.getSaleById(saleId);

        if (items.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("500",
                    "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
        }

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
    }
}
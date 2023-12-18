package octagon.retail.service.sale;

import octagon.retail.entity.ItemPrices;
import octagon.retail.entity.sale.SaleItems;
import octagon.retail.model.sale.TopTenItems;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ItemPriceRepository;
import octagon.retail.repository.sale.SaleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.List;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    public ResponseEntity<ResponseModel<SaleItems>> saveItem(SaleItems saleItem) {

        SaleItems item = saleItemRepository.getSaleIdItemId(saleItem.getSaleId(), saleItem.getItemCodeId());
        if (item == null) {

            BigDecimal amount = saleItem.getUnitSalePrice();
            BigDecimal totalAmount = saleItem.getQty().multiply(amount);

            saleItem.setTotalSalePrice(totalAmount);
            saleItemRepository.save(saleItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай мөр нэмлээ", true, saleItem));
        }

        BigDecimal qty = item.getQty().add(saleItem.getQty());
        BigDecimal amount = saleItem.getUnitSalePrice();
        BigDecimal totalAmount = qty.multiply(amount);

        item.setQty(qty);
        item.setUnitSalePrice(amount);
        item.setTotalSalePrice(totalAmount);
        saleItemRepository.save(item);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгалагдлаа", true, item));
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
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
        }

        items.forEach(saleItem -> {
            saleItem.setIsDeleted(true);
        });

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
    }

    public ResponseEntity<ResponseModel<SaleItems>> deleteItem(Long itemId) {
        saleItemRepository.deleteById(itemId);
        SaleItems saleItem = saleItemRepository.findById(itemId).orElse(null);
        if (saleItem == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, saleItem));
    }

    public ResponseEntity<ResponseModel<List<SaleItems>>> manySaleId(Long saleId) {
        List<SaleItems> items = saleItemRepository.getSaleById(saleId);

        if (items.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
        }

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
    }

    public ResponseEntity<ResponseModel<Object>> getTopTenItems() {
        List<TopTenItems> data = saleItemRepository.getTopTenItems();

        if (data != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, data));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Data Error", false, null));

    }
}
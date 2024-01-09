package octagon.retail.service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.ItemPrices;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.ItemPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCodeService {

    @Autowired
    private IItemCodeRepository itemCodeRepository;

    @Autowired
    private ItemPriceRepository itemPriceRepository;

    public ResponseEntity<ResponseModel<ItemCodes>> saveItemCode(ItemCodes itemCodes) {
        try {
            itemCodeRepository.save(itemCodes);

            ItemCodes savedItemcodes = itemCodeRepository.getItemByBarcode(itemCodes.getBarcode()).orElse(null);
            if (savedItemcodes != null) {
                ItemPrices itemPrices = createItemPricesFromItemCodes(savedItemcodes);
                itemPriceRepository.save(itemPrices);
            }
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгаллаа", true, itemCodes));
        } catch (Exception e) {
            // Handle exceptions, e.g., database-related errors
            return ResponseEntity.ok(new ResponseModel<>("500", "Database error: " + e.getMessage(), false, null));
        }
    }

    private ItemPrices createItemPricesFromItemCodes(ItemCodes itemCodes) {
        ItemPrices itemPrices = new ItemPrices();
        itemPrices.setItemCodeId(itemCodes.getId());
        itemPrices.setItemId(itemCodes.getItemId());
        itemPrices.setQty(itemCodes.getQty());
        itemPrices.setUnitSalePrice(itemCodes.getSellPrice());
        itemPrices.setUnitCostPrice(itemCodes.getPurchasePrice());
        itemPrices.setBranchId(itemCodes.getBranchId());
        return itemPrices;
    }

    public ResponseEntity<ResponseModel<ItemCodes>> updateItemCode(ItemCodes updateItemCodes) {
        ItemCodes existingItemCodes = itemCodeRepository.findById(updateItemCodes.getId()).orElse(null);
        if (existingItemCodes != null) {
            existingItemCodes.setItemId(updateItemCodes.getItemId());
            existingItemCodes.setName(updateItemCodes.getName());
            existingItemCodes.setQty(updateItemCodes.getQty());
            existingItemCodes.setBarcode(updateItemCodes.getBarcode());
            existingItemCodes.setBranchId(updateItemCodes.getBranchId());
            existingItemCodes.setMeasureId(updateItemCodes.getMeasureId());
            existingItemCodes.setSellPrice(updateItemCodes.getSellPrice());
            existingItemCodes.setPurchasePrice(updateItemCodes.getPurchasePrice());
            existingItemCodes.setIsDeleted(updateItemCodes.getIsDeleted());
            itemCodeRepository.save(existingItemCodes);

            ItemPrices itemPrices = itemPriceRepository.exist(existingItemCodes.getId());
            if (itemPrices != null) {
                ItemPrices convertedItemPrices = createItemPricesFromItemCodes(updateItemCodes);
                convertedItemPrices.setId(itemPrices.getId());
                itemPriceRepository.save(convertedItemPrices);
            }

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгаллаа", true, existingItemCodes));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин орорлдон уу", false, null));
    }

    public ResponseEntity<ResponseModel<ItemCodes>> getItemCodeById(Long itemCodeId) {
        ItemCodes itemCodes = itemCodeRepository.findById(itemCodeId).orElse(null);
        if (itemCodes != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemCodes));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemCodes>>> getAllItemCodes() {
        List<ItemCodes> itemCodes = itemCodeRepository.findAll();
        if (itemCodes != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemCodes));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<ItemCodes>> getItemCodeByBarcode(String barcode) {
        ItemCodes itemCodes = itemCodeRepository.getItemByBarcode(barcode).orElse(null);
        if (itemCodes != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemCodes));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemCodes>>> getItemCodeByBarcodeLike(String barcode) {
        List<ItemCodes> itemCodes = itemCodeRepository.getItemCodesByBarcodeIsLike(barcode);
        if (itemCodes != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemCodes));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<ItemCodes>> deleteItemCodeById(Long itemCodeId) {
        itemCodeRepository.deleteById(itemCodeId);
        ItemCodes itemCode = itemCodeRepository.findById(itemCodeId).orElse(null);
        if (itemCode == null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдно уу", false, itemCode));
    }
}

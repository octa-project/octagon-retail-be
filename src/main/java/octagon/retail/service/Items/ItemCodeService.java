package octagon.retail.service.Items;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.ItemPrices;
import octagon.retail.model.item.CustomItemCodeModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.ItemPriceRepository;
import octagon.retail.response.ResponseModel;

import org.apache.tomcat.util.digester.ArrayStack;
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

            ItemCodes itemcode = itemCodeRepository.getItemByBarcode(itemCodes.getBarcode()).orElse(null);
            if (itemcode != null) {
                return ResponseEntity.ok(new ResponseModel<>("500", "Database error: " + "Barcode exist", false, null));
            }
            itemCodeRepository.save(itemCodes);
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
        itemPrices.setUnitCostPrice(itemCodes.getCostPrice());
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
            existingItemCodes.setCostPrice(updateItemCodes.getCostPrice());
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

    public ResponseEntity<ResponseModel<List<CustomItemCodeModel>>> getCustomItemCodesByLike(String Barcode,
            String Name) {
        try {
            List<CustomItemCodeModel> list = new ArrayStack<>();
            if ((!Barcode.isEmpty() && !Name.isEmpty()) || (Barcode.isEmpty() && Name.isEmpty())) {

                return ResponseEntity.ok(new ResponseModel<>("500", "Invalid input", false,
                        null));
            } else if (!Barcode.isEmpty() && Name.isEmpty()) {
                var itemCodes = itemCodeRepository.findByBarcodeLike(Barcode);

                list = itemCodes;
            } else {
                var itemCodes = itemCodeRepository.findByNameLike(Name);

                list = itemCodes;
            }
            if (list.isEmpty())
                return ResponseEntity.ok(new ResponseModel<>("500", "No items", false,
                        null));

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    list));
        } catch (Exception e) {

            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }

    public ResponseEntity<ResponseModel<List<CustomItemCodeModel>>> getCustomItemCodes() {
        try {
            var itemCodes = itemCodeRepository.getCustomItemCodes();
            if (itemCodes.isEmpty())
                return ResponseEntity.ok(new ResponseModel<>("500", "No items", false,
                        itemCodes));

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    itemCodes));
        } catch (Exception e) {

            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }

}

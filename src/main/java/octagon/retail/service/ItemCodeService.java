package octagon.retail.service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCodeService {

    @Autowired
    private IItemCodeRepository itemCodeRepository;

    public ResponseEntity<ResponseModel<ItemCodes>> saveItemCode(ItemCodes itemCodes) {
        itemCodeRepository.save(itemCodes);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгаллаа", true, itemCodes));
    }

    public ResponseEntity<ResponseModel<ItemCodes>> updateItemCodes(ItemCodes updateItemCodes) {
        ItemCodes existingItemCodes = itemCodeRepository.findById(updateItemCodes.getId()).orElse(null);
        if (existingItemCodes != null) {
            existingItemCodes.setItemId(updateItemCodes.getItemId());
            existingItemCodes.setName(updateItemCodes.getName());
            existingItemCodes.setQty(updateItemCodes.getQty());
            existingItemCodes.setBarcode(updateItemCodes.getBarcode());
            existingItemCodes.setBranchId(updateItemCodes.getBranchId());
            existingItemCodes.setMeasureId(updateItemCodes.getMeasureId());
            existingItemCodes.setSellPrice(updateItemCodes.getSellPrice());
            existingItemCodes.setId(updateItemCodes.getId());
            existingItemCodes.setIsDeleted(updateItemCodes.getIsDeleted());

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

    public ResponseEntity<ResponseModel<List<ItemCodes>>> getAllItemCodes(){
        List<ItemCodes>itemCodes = itemCodeRepository.findAll();
        if (itemCodes != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemCodes));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
    public ResponseEntity<ResponseModel<ItemCodes>> getItemCodeByBarcode(String barcode) {
        ItemCodes itemCodes = itemCodeRepository.getItemByBarcode(barcode);
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
    public ResponseEntity<ResponseModel<ItemCodes>> deleteItemCodeById(Long itemCodeId){
        itemCodeRepository.deleteById(itemCodeId);
        ItemCodes itemCodes = itemCodeRepository.findById(itemCodeId).orElse(null);
        if (itemCodeId == null)
            return ResponseEntity.ok(new ResponseModel<>("200","Амжилттай",true,null));
        return ResponseEntity.ok(new ResponseModel<>("500","Амжилтгүй - алдаа гарлаа ахин оролдно уу",false,itemCodes));
    }
}

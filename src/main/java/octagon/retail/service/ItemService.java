package octagon.retail.service;

import octagon.retail.entity.Items;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public ResponseEntity<ResponseModel<Items>> saveItem(Items item) {
        Items localItem = itemRepository.getItemByCode(item.getCode());
        if (localItem == null) {
            itemRepository.save(item);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, item));
        }
        return ResponseEntity.ok(new ResponseModel<>("404", "Амжилтгүй - Өмнө нь бүртгэгдсэн бараа байна!", false, null));
    }

    public ResponseEntity<ResponseModel<Items>> updateItem(Items item) {
        Items existingItem = itemRepository.findById(item.getId()).orElse(null);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setCode(item.getCode());
            existingItem.setIsActive(item.getIsActive());
            existingItem.setMeasureId(item.getMeasureId());
            existingItem.setIsActive(item.getIsActive());
            existingItem.setIsDeleted(item.getIsDeleted());
            existingItem.setBranchId(item.getBranchId());
            itemRepository.save(existingItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingItem));
        }
        return ResponseEntity.ok(new ResponseModel<>("404", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Items>> getItemById(Long itemId) {
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", true, item));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<List<Items>>> getAllItems() {
        List<Items> items = itemRepository.findAll();
        if (items != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
        else
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Items>> getItemByCode(String code) {
        Items item = itemRepository.getItemByCode(code);
        if (item != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, item));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<Items>> deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, item));
        }
    }
}

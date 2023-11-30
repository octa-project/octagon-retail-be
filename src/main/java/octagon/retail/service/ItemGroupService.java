package octagon.retail.service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemGroupService {

    @Autowired
    private IItemGroupRepository itemGroupRepository;

    public ResponseEntity<ResponseModel<ItemGroups>> saveItemGroup(ItemGroups itemGroups) {
        itemGroupRepository.save(itemGroups);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemGroups));
    }

    public ResponseEntity<ResponseModel<ItemGroups>> updateItemGroups(ItemGroups itemGroups) {
        ItemGroups existingItemGroups = itemGroupRepository.findById(itemGroups.getId()).orElse(null);
        if (existingItemGroups != null) {
            existingItemGroups.setName(itemGroups.getName());
            existingItemGroups.setIsDeleted(itemGroups.getIsDeleted());
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingItemGroups));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Not Found", false, null));
    }

    public ResponseEntity<ResponseModel<ItemGroups>> getItemGroupById(Long itemGroupsId) {
        ItemGroups itemGroups = itemGroupRepository.findById(itemGroupsId).orElse(null);
        if (itemGroups != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemGroups));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemGroups>>> getAllGroups() {
        List<ItemGroups>itemGroups = itemGroupRepository.findAll();
        if (itemGroups != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemGroups));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
    public ResponseEntity<ResponseModel<ItemGroups>> deleteItemGroupById(Long itemGroupId){
        itemGroupRepository.deleteById(itemGroupId);
        ItemGroups itemGroup = itemGroupRepository.findById(itemGroupId).orElse(null);
        if (itemGroup == null)
            return ResponseEntity.ok(new ResponseModel<>("200","Амжилттай",true,null));
        return ResponseEntity.ok(new ResponseModel<>("500","Амжилтгүй - алдаа гарлаа ахин оролдно уу",false,itemGroup));
    }

}

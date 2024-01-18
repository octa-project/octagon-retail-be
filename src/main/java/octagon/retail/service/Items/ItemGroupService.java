package octagon.retail.service.Items;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.repository.IItemGroupRepository;
import octagon.retail.response.ResponseModel;

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

    public ResponseEntity<ResponseModel<ItemGroups>> updateItemGroup(ItemGroups itemGroup) {
        ItemGroups existingItemGroup = itemGroupRepository.findById(itemGroup.getId()).orElse(null);
        if (existingItemGroup != null) {
            existingItemGroup.setName(itemGroup.getName());
            existingItemGroup.setCode(itemGroup.getCode());
            existingItemGroup.setColor(itemGroup.getColor());
            existingItemGroup.setBranchId(itemGroup.getBranchId());
            itemGroupRepository.save(existingItemGroup);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingItemGroup));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Not Found", false, null));
    }

    public ResponseEntity<ResponseModel<ItemGroups>> getItemGroupsById(Long itemGroupsId) {
        ItemGroups itemGroup = itemGroupRepository.findById(itemGroupsId).orElse(null);
        if (itemGroup != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemGroup));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemGroups>>> getAllGroups() {
        List<ItemGroups> itemGroups = itemGroupRepository.findAll();
        if (itemGroups != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, itemGroups));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<ItemGroups>> deleteItemGroupById(Long itemGroupId) {
        itemGroupRepository.deleteById(itemGroupId);
        ItemGroups itemGroup = itemGroupRepository.findById(itemGroupId).orElse(null);
        if (itemGroup == null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдно уу", false, itemGroup));
    }

}

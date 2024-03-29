package octagon.retail.contoller;

import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.Items;
import octagon.retail.repository.IItemGroupRepository;
import octagon.retail.response.ResponseModel;
import octagon.retail.service.Items.ItemGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemgroup")
@CrossOrigin(origins = "*")
public class ItemGroupController {

    @Autowired
    private ItemGroupService itemGroupService;

    @PostMapping("save-itemGroup")
    public ResponseEntity<ResponseModel<ItemGroups>> saveItemGroup(@RequestBody ItemGroups itemGroup) {
        return itemGroupService.saveItemGroup(itemGroup);
    }

    @PostMapping("update-itemGroup")
    public ResponseEntity<ResponseModel<ItemGroups>> updateItemGroup(@RequestBody ItemGroups itemGroup) {
        return itemGroupService.updateItemGroup(itemGroup);
    }

    @GetMapping("get-itemGroup-by-id")
    public ResponseEntity<ResponseModel<ItemGroups>> getItemGroupsById(@RequestParam("id") Long itemGroupId) {
        return itemGroupService.getItemGroupsById(itemGroupId);
    }

    @GetMapping("get-all-itemGroups")
    public ResponseEntity<ResponseModel<List<ItemGroups>>> getAllByItemGroups() {
        return itemGroupService.getAllGroups();
    }

    @PostMapping("delete-itemGroup-by-id")
    public ResponseEntity<ResponseModel<ItemGroups>> deleteItemGroupById(@RequestParam("id") Long itemGroupId) {
        return itemGroupService.deleteItemGroupById(itemGroupId);
    }
}

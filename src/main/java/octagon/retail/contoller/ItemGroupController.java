package octagon.retail.contoller;

import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.Items;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemGroupRepository;
import octagon.retail.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemgroup")
public class ItemGroupController {

    @Autowired
    private ItemGroupService itemGroupService;

    @PostMapping("save-itemGroup")
    public ResponseEntity<ResponseModel<ItemGroups>> saveItemGroup(@RequestBody ItemGroups itemGroup){
        return itemGroupService.saveItemGroup(itemGroup);
    }
    @PostMapping("update-itemGroup")
    public ResponseEntity<ResponseModel<ItemGroups>> getAllItemGroup(@RequestBody ItemGroups itemGroup){
        return itemGroupService.updateItemGroups(itemGroup);
    }
    @GetMapping("get-itemGroup-by-id")
    public ResponseEntity<ResponseModel<ItemGroups>> getItemGroupById(@RequestParam("id") Long itemGroupId){
        return itemGroupService.getItemGroupById(itemGroupId);
    }
    @GetMapping("get-all-itemGroups")
    public ResponseEntity<ResponseModel<List<ItemGroups>>> getAllByItemGroups(){
        return itemGroupService.getAllGroups();
    }
    @DeleteMapping("delete-itemGroup-by-id")
    public ResponseEntity<ResponseModel<ItemGroups>> deleteAllByItemGroup(@RequestParam("id") Long itemGroupId){
        return itemGroupService.deleteItemGroupById(itemGroupId);
    }
}

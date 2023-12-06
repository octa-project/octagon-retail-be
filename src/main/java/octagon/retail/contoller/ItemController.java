package octagon.retail.contoller;

import jakarta.validation.*;
import octagon.retail.entity.Items;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("save-item")
    public ResponseEntity<ResponseModel<Items>> saveItemPrice(@RequestBody @Valid Items item){
        return itemService.saveItem(item);
    }
    @PostMapping("update-item")
    public ResponseEntity<ResponseModel<Items>> getAllItemPrice(@RequestBody @Valid Items item){
        return itemService.updateItem(item);
    }
    @GetMapping("get-all-items")
    public ResponseEntity<ResponseModel<List<Items>>> getAllByItemId() {
        return itemService.getAllItems();
    }

    @GetMapping("get-item-by-id")
    public ResponseEntity<ResponseModel<Items>> getItemById(@RequestParam("id") Long itemId){
    return itemService.getItemById(itemId);
    }

    @GetMapping("get-item-by-code")
    public ResponseEntity<ResponseModel<Items>> getItemByCode(@RequestParam("code") String code){
        return itemService.getItemByCode(code);
    }
    @PostMapping("delete-item-by-id")
    public ResponseEntity<ResponseModel<Items>> DeleteAllByItemId(@RequestParam("id") Long itemId) {
        return itemService.deleteItemById(itemId);
    }
}
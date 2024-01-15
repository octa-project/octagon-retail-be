package octagon.retail.contoller;

import jakarta.validation.Valid;
import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemGroups;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.ItemCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemcode")
@CrossOrigin(origins = "*")
public class ItemCodeController {

    @Autowired
    private ItemCodeService itemCodeService;

    @PostMapping("save-itemCode")
    public ResponseEntity<ResponseModel<ItemCodes>> saveItemCode(@RequestBody @Valid ItemCodes itemCode) {
        return itemCodeService.saveItemCode(itemCode);
    }

    @PostMapping("update-itemCode")
    public ResponseEntity<ResponseModel<ItemCodes>> updateItemCode(@RequestBody @Valid ItemCodes itemCode) {
        return itemCodeService.updateItemCode(itemCode);
    }

    @GetMapping("get-itemCode-by-id")
    public ResponseEntity<ResponseModel<ItemCodes>> getAllByItemCodeId(@RequestParam("id") Long itemCodeId) {
        return itemCodeService.getItemCodeById(itemCodeId);
    }

    @GetMapping("get-all-itemCodes")
    public ResponseEntity<ResponseModel<List<ItemCodes>>> getAllByItemCodes() {
        return itemCodeService.getAllItemCodes();
    }

    @GetMapping("get-itemCode-by-barcode")
    public ResponseEntity<ResponseModel<ItemCodes>> GetItemCodeByBarcode(@RequestParam("barcode") String barcode) {
        return itemCodeService.getItemCodeByBarcode(barcode);
    }

    @GetMapping("get-itemCode-by-barcode-like")
    public ResponseEntity<ResponseModel<List<ItemCodes>>> GetItemCodeByBarcodeLike(@RequestParam("barcode") String barcode) {
        return itemCodeService.getItemCodeByBarcodeLike(barcode);
    }


    @PostMapping("delete-itemCode-by-id")
    public ResponseEntity<ResponseModel<ItemCodes>> deleteItemCodeById(@RequestParam("id") Long itemCodeId) {
        return itemCodeService.deleteItemCodeById(itemCodeId);
    }
}

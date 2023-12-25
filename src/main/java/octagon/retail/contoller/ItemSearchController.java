package octagon.retail.contoller;

import jakarta.validation.Valid;
import octagon.retail.entity.ItemPrices;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/item_search")
@CrossOrigin(origins = "*")
public class ItemSearchController {

    @Autowired
    private ItemSearchService itemSearchService;
    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<ItemPrices>> getOne(String itemBarCode){
        return itemSearchService.getOne(itemBarCode);
    }
    @GetMapping("get-like-barcode")
    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikeBarcode(String itemBarCode){
        return itemSearchService.getLikeBarcode(itemBarCode);
    }
    @GetMapping("get-like-name")
    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikeName(String itemName){
        return itemSearchService.getLikeName(itemName);
    }
    @GetMapping("get-like-price")
    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikePrice(String itemPrice){
        return itemSearchService.getLikePrice(itemPrice);
    }
}
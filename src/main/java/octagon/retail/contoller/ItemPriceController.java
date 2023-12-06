package octagon.retail.contoller;

import jakarta.validation.Valid;
import octagon.retail.entity.ItemPrices;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.ItemPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item_price")
@CrossOrigin(origins = "*")
public class ItemPriceController {

    @Autowired
    private ItemPriceService itemPriceService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<ItemPrices>> save(@Valid @RequestBody ItemPrices price) {
        return itemPriceService.savePrice(price);
    }
    @PutMapping("update")
    public ResponseEntity<ResponseModel<ItemPrices>> save(@RequestBody ItemPrices price, Long id) {
        return itemPriceService.updatePrice(price,id);
    }
    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<ItemPrices>> getOne(Long id){
        return itemPriceService.getOne(id);
    }
    @GetMapping("get-many-item-id")
    public ResponseEntity<ResponseModel<List<ItemPrices>>> getByItemId(Long id){
        return itemPriceService.getByItemId(id);
    }
    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<ItemPrices>>> getMany(){
        return itemPriceService.getMany();
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<ItemPrices>> delete(Long id){
        return itemPriceService.delete(id);
    }
}
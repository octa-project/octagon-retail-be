package octagon.retail.contoller;

import jakarta.validation.Valid;
import octagon.retail.entity.ItemPrice;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.ItemPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item_price")
@CrossOrigin(origins = "*", allowedHeaders = "GET, POST, PUT, DELETE")
public class ItemPriceController {

    @Autowired
    private ItemPriceService itemPriceService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<ItemPrice>> save(@Valid @RequestBody ItemPrice bank) {
        return itemPriceService.savePrice(bank);
    }
    @PutMapping("update")
    public ResponseEntity<ResponseModel<ItemPrice>> save(@Valid @RequestBody ItemPrice bank, @RequestParam("id") Long id) {
        return itemPriceService.updatePrice(bank,id);
    }
    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<ItemPrice>> getOne(@RequestParam("id") Long id){
        return itemPriceService.getOne(id);
    }
    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<ItemPrice>>> getMany(){
        return itemPriceService.getMany();
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<ItemPrice>> delete(@RequestParam("id") Long id){
        return itemPriceService.delete(id);
    }
}

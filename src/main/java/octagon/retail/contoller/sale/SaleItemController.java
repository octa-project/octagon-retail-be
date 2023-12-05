package octagon.retail.contoller.sale;

import jakarta.validation.Valid;
import octagon.retail.entity.sale.SaleItems;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.sale.SaleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale/items")
@CrossOrigin(origins = "*")
public class SaleItemController {

    @Autowired
    private SaleItemService saleItemService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<SaleItems>> save(@Valid @RequestBody SaleItems item) {
        return saleItemService.saveItem(item);
    }

    @PutMapping("update")
    public ResponseEntity<ResponseModel<SaleItems>> update(@RequestParam("id") Long id, @RequestBody SaleItems item) {
        return saleItemService.updateItem(id,item);
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<SaleItems>> getSaleItemById(@RequestParam("id") Long id) {
        return saleItemService.deleteItem(id);
    }
    @GetMapping("get-many-sale-id")
    public ResponseEntity<ResponseModel<List<SaleItems>>> getManySaleId(@RequestParam("id") Long saleId){
        return saleItemService.manySaleId(saleId);
    }
}

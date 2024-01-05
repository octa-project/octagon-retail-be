package octagon.retail.contoller.sale;

import jakarta.validation.Valid;
import octagon.retail.entity.SaleItems;
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
    public ResponseEntity<ResponseModel<SaleItems>> update(@RequestBody SaleItems item, Long id) {
        return saleItemService.updateItem(id, item);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<SaleItems>> getSaleItemById(Long id) {
        return saleItemService.deleteItem(id);
    }

    @GetMapping("get-many-sale-id")
    public ResponseEntity<ResponseModel<List<SaleItems>>> getManySaleId(Long saleId) {
        return saleItemService.manySaleId(saleId);
    }

    @GetMapping("/get-top-ten-item")
    public ResponseEntity<ResponseModel<Object>> getTopTenItems() {
        return saleItemService.getTopTenItems();
    }
}
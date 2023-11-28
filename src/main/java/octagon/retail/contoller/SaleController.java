package octagon.retail.contoller;

import octagon.retail.entity.Sales;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("save-sale")
    public ResponseEntity<ResponseModel<Sales>> saveSaleItem(@RequestBody Sales sale) {
        return saleService.saveSale(sale);
    }

    @PostMapping("update-sale")
    public ResponseEntity<ResponseModel<Sales>> updateSaleItem(@RequestBody Sales sale) {
        return saleService.updateSale(sale);
    }

    @PostMapping("update-sale-paid")
    public ResponseEntity<ResponseModel<Sales>> updateSaleItem(@RequestParam("id") Long id) {
        return saleService.updateSaleIsPaid(id);
    }

    @DeleteMapping("delete-sale-by-id")
    public ResponseEntity<ResponseModel<Sales>> getSaleItemById(@RequestParam("id") Long id) {
        return saleService.deleteSaleById(id);
    }

    @GetMapping("get-sales")
    public ResponseEntity<ResponseModel<List<Sales>>> getSaleItemsBySaleId() {
        return saleService.getSales();
    }

    @GetMapping("get-sale-by-id")
    public ResponseEntity<ResponseModel<Sales>> getSaleItemsBySaleId(@RequestParam("id") Long id) {
        return saleService.getSaleById(id);
    }
}

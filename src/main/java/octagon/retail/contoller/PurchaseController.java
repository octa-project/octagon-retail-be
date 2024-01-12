package octagon.retail.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import octagon.retail.entity.Purchase;
import octagon.retail.entity.Sales;
import octagon.retail.model.PurchaseModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.purchase.PurchaseService;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "*")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/save")
    public ResponseEntity<ResponseModel<Purchase>> savePurchase(@RequestBody @Valid PurchaseModel body) {
        return purchaseService.savePurchase(body);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModel<Purchase>> updatePurchase(@RequestBody @Valid PurchaseModel body) {
        return purchaseService.updatePurchase(body);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel<Purchase>> deletePurchase(@RequestParam Long id) {
        return purchaseService.deletePurchase(id);
    }

    @PostMapping("/pay")
    public ResponseEntity<ResponseModel<Purchase>> PurchasePay(@RequestParam Long id) {
        return purchaseService.purchasePay(id);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<ResponseModel<PurchaseModel>> getPurchase(@RequestParam Long id) {
        return purchaseService.getPurchase(id);
    }

    @GetMapping("get-all-by-date")
    public ResponseEntity<ResponseModel<List<PurchaseModel>>> getManyByDate(@RequestParam String startDate) {
        return purchaseService.getAllByDate(startDate);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModel<List<PurchaseModel>>> getPurchase() {
        return purchaseService.getAllPurchases();
    }
}

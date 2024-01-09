package octagon.retail.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import octagon.retail.entity.Purchase;
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
    public ResponseEntity<ResponseModel<Purchase>> savePurchase(@RequestBody PurchaseModel body) {
        return purchaseService.savePurchase(body);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<ResponseModel<PurchaseModel>> getPurchase(@RequestParam Long id) {
        return purchaseService.getPurchase(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModel<List<PurchaseModel>>> getPurchase() {
        return purchaseService.getAllPurchases();
    }
}
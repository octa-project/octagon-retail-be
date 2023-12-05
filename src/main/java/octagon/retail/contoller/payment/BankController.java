package octagon.retail.contoller.payment;

import jakarta.validation.Valid;
import octagon.retail.entity.payment.Banks;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.payment.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*")
public class BankController {

    @Autowired
    private BankService bankService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<Banks>> save(@Valid @RequestBody Banks bank) {
        return bankService.saveBank(bank);
    }
    @PutMapping("update")
    public ResponseEntity<ResponseModel<Banks>> save(@Valid @RequestBody Banks bank, @RequestParam("id") Long id) {
        return bankService.updateBank(bank,id);
    }
    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<Banks>> getOne(@RequestParam("id") Long id){
        return bankService.getOne(id);
    }
    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<Banks>>> getMany(){
        return bankService.getMany();
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<Banks>> delete(@RequestParam("id") Long id){
        return bankService.delete(id);
    }
}

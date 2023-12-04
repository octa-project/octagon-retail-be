package octagon.retail.contoller.bank;

import jakarta.validation.Valid;
import octagon.retail.entity.bank.Banks;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

package octagon.retail.contoller.payment;

import jakarta.validation.Valid;
import octagon.retail.entity.payment.Transactions;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.payment.BankService;
import octagon.retail.service.payment.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<Transactions>> save(@Valid @RequestBody Transactions transaction) {
        return transactionService.saveTransaction(transaction);
    }
    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<Transactions>> getOne(@RequestParam("id") Long id){
        return transactionService.getOne(id);
    }
    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<Transactions>>> getMany(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate){
        return transactionService.getMany(startDate,endDate);
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<Transactions>> delete(@RequestParam("id") Long id){
        return transactionService.delete(id);
    }
}

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

    @PutMapping("update")
    public ResponseEntity<ResponseModel<Transactions>> update(@Valid @RequestBody Transactions transaction, Long id) {
        return transactionService.updateTransaction(transaction, id);
    }

    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<Transactions>> getOne(Long id) {
        return transactionService.getOne(id);
    }

    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<Transactions>>> getMany(
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return transactionService.getMany(startDate, endDate);
    }

    // @GetMapping("get-by-sale-id")
    // public ResponseEntity<ResponseModel<List<Transactions>>> getBySaleId(Long
    // id){
    // return transactionService.getBySaleId(id);
    // }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<Transactions>> delete(Long id) {
        return transactionService.delete(id);
    }
}

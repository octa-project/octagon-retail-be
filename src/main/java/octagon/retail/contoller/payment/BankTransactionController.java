// package octagon.retail.contoller.payment;

// import jakarta.validation.Valid;
// import octagon.retail.entity.payment.BankTransactions;
// import octagon.retail.reponse.ResponseModel;
// import octagon.retail.service.payment.BankTransactionService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Date;
// import java.util.List;

// @RestController
// @RequestMapping("/bank/transaction")
// @CrossOrigin(origins = "*")
// public class BankTransactionController {

// @Autowired
// private BankTransactionService bankTransactionService;
// @PostMapping("save")
// public ResponseEntity<ResponseModel<BankTransactions>> save(@Valid
// @RequestBody BankTransactions transaction) {
// return bankTransactionService.saveBankTransaction(transaction);
// }
// @PutMapping("update")
// public ResponseEntity<ResponseModel<BankTransactions>> update(@Valid
// @RequestBody BankTransactions transaction, Long id){
// return bankTransactionService.updateBankTransaction(transaction,id);
// }
// @GetMapping("get-one")
// public ResponseEntity<ResponseModel<BankTransactions>> getOne(Long id){
// return bankTransactionService.getOne(id);
// }
// @GetMapping("get-many")
// public ResponseEntity<ResponseModel<List<BankTransactions>>>
// getMany(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate){
// return bankTransactionService.getMany(startDate,endDate);
// }
// @GetMapping("get-by-sale-id")
// public ResponseEntity<ResponseModel<List<BankTransactions>>> getBySaleId(Long
// id){
// return bankTransactionService.getBySaleId(id);
// }
// @DeleteMapping("delete")
// public ResponseEntity<ResponseModel<BankTransactions>> delete(Long id){
// return bankTransactionService.delete(id);
// }
// }

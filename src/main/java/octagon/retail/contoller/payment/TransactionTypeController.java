package octagon.retail.contoller.payment;

import jakarta.validation.Valid;
import octagon.retail.entity.payment.TransactionType;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.payment.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/types")
@CrossOrigin(origins = "*")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;
    @PostMapping("save")
    public ResponseEntity<ResponseModel<TransactionType>> save(@Valid @RequestBody TransactionType type){
        return transactionTypeService.saveType(type);
    }
    @PutMapping("update")
    public ResponseEntity<ResponseModel<TransactionType>> update(@Valid @RequestBody TransactionType type, @RequestParam("id") Long id){
        return transactionTypeService.updateType(type,id);
    }
    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<TransactionType>>> getMany(){
        return transactionTypeService.getMany();
    }
    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<TransactionType>> delete(@RequestParam("id") Long id){
        return transactionTypeService.delete(id);
    }
}

package octagon.retail.service.payment;

import octagon.retail.entity.payment.Banks;
import octagon.retail.entity.payment.TransactionType;
import octagon.retail.entity.sale.SaleItems;
import octagon.retail.entity.sale.Sales;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.payment.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    public ResponseEntity<ResponseModel<TransactionType>> saveType(TransactionType type) {
        TransactionType checkName = transactionTypeRepository.exist(type.getName());
        if(checkName == null) {
            TransactionType types = transactionTypeRepository.save(type);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, types));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s нэртэй төлбөрийн төрөл бүртгэгдсэн байна.".formatted(type.getName()), false, null));
    }
    public ResponseEntity<ResponseModel<TransactionType>> updateType(TransactionType update, Long id) {
        TransactionType type = transactionTypeRepository.findById(id).orElse(null);
        if (type != null) {
            type.setName(update.getName());
            type.setParentId(update.getParentId());
            transactionTypeRepository.save(type);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, type));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
    public ResponseEntity<ResponseModel<List<TransactionType>>> getMany() {
        List<TransactionType> types = transactionTypeRepository.get();
        return ResponseEntity.ok(new ResponseModel<>("200","Амжилттай", true, types));
    }

    public ResponseEntity<ResponseModel<TransactionType>> delete(Long id) {
        transactionTypeRepository.deleteById(id);
        TransactionType type = transactionTypeRepository.findById(id).orElse(null);
        if (type == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, type));
    }


}

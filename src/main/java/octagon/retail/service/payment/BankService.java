package octagon.retail.service.payment;

import octagon.retail.entity.payment.Banks;
import octagon.retail.entity.payment.TransactionType;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.payment.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;
    public ResponseEntity<ResponseModel<Banks>> saveBank(Banks bank) {
        Banks checkBank = bankRepository.exist(bank.getCode());
        if(checkBank == null) {
            Banks banks = bankRepository.save(bank);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, banks));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой банкны мэдээлэл бүртгэгдсэн байна.".formatted(bank.getCode()), false, null));
    }

    public ResponseEntity<ResponseModel<Banks>> getOne(Long id) {
        Banks banks = bankRepository.findById(id).orElse(null);
        if(banks != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, banks));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой банкны мэдээлэл олдсонгүй.".formatted(id), false, null));
    }

    public ResponseEntity<ResponseModel<List<Banks>>> getMany() {
        List<Banks> banks = bankRepository.findAll();
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, banks));
    }

    public ResponseEntity<ResponseModel<Banks>> delete(Long id) {
        bankRepository.deleteById(id);
        Banks bank = bankRepository.findById(id).orElse(null);
        if (bank == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, bank));
    }

    public ResponseEntity<ResponseModel<Banks>> updateBank(Banks update, Long id) {
        Banks bank = bankRepository.findById(id).orElse(null);
        if (bank != null) {
            bank.setName(update.getName());
            bank.setCode(update.getCode());
            bankRepository.save(bank);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, bank));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
}
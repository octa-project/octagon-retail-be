package octagon.retail.service.bank;

import octagon.retail.entity.bank.Banks;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}

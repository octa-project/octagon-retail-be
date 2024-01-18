package octagon.retail.service.payment;

import octagon.retail.entity.payment.BankTransactions;
import octagon.retail.repository.payment.BankTransactionRepository;
import octagon.retail.response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public ResponseEntity<ResponseModel<BankTransactions>> saveBankTransaction(BankTransactions transaction) {
        BankTransactions transactions = bankTransactionRepository.save(transaction);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, transactions));
    }

    public ResponseEntity<ResponseModel<BankTransactions>> updateBankTransaction(BankTransactions update, Long id) {
        BankTransactions transaction = bankTransactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setBankId(update.getBankId());
            transaction.setName(update.getName());
            transaction.setPan(update.getPan());
            transaction.setOperationCode(update.getOperationCode());
            transaction.setEntryMode(update.getEntryMode());
            transaction.setRrn(update.getRrn());
            transaction.setBankId(update.getBankId());
            transaction.setDbRefNo(update.getDbRefNo());
            transaction.setTerminalId(update.getTerminalId());
            transaction.setAmount(update.getAmount());
            transaction.setTraceNo(update.getTraceNo());
            bankTransactionRepository.save(transaction);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transaction));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<BankTransactions>> getOne(Long id) {
        BankTransactions transactions = bankTransactionRepository.findById(id).orElse(null);
        if (transactions != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, transactions));
        }
        return ResponseEntity.ok(new ResponseModel<>("500",
                "Амжилтгүй. %s кодтой гүйлгээний мэдээлэл олдсонгүй.".formatted(id), false, null));
    }

    public ResponseEntity<ResponseModel<List<BankTransactions>>> getMany(Date startDate, Date endDate) {
        List<BankTransactions> transactions = bankTransactionRepository.getMany(startDate, endDate);
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transactions));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<List<BankTransactions>>> getBySaleId(Long id) {
        List<BankTransactions> transactions = bankTransactionRepository.getBySaleId(id);
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transactions));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<BankTransactions>> delete(Long id) {
        BankTransactions transaction = bankTransactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            bankTransactionRepository.save(transaction);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transaction));
        }
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, transaction));
    }
}

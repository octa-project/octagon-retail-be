package octagon.retail.service.payment;

import octagon.retail.entity.Sales;
import octagon.retail.entity.payment.Banks;
import octagon.retail.entity.payment.TransactionType;
import octagon.retail.entity.payment.Transactions;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.payment.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<ResponseModel<Transactions>> saveTransaction(Transactions transaction) {
        Transactions transactions = transactionRepository.save(transaction);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, transactions));
    }

    public ResponseEntity<ResponseModel<Transactions>> getOne(Long id) {
        Transactions transactions = transactionRepository.findById(id).orElse(null);
        if (transactions != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, transactions));
        }
        return ResponseEntity.ok(new ResponseModel<>("500",
                "Амжилтгүй. %s кодтой гүйлгээний мэдээлэл олдсонгүй.".formatted(id), false, null));
    }

    public ResponseEntity<ResponseModel<Transactions>> delete(Long id) {
        transactionRepository.deleteById(id);
        Transactions transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, transaction));
    }

    public ResponseEntity<ResponseModel<List<Transactions>>> getMany(Date startDate, Date endDate) {
        List<Transactions> transactions = transactionRepository.getMany(startDate, endDate);
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transactions));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<Transactions>> updateTransaction(Transactions update, Long id) {
        Transactions transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setSaleId(update.getSaleId());
            transaction.setTransactionName(update.getTransactionName());
            transaction.setAmount(update.getAmount());
            transaction.setBankTransactionId(update.getBankTransactionId());
            transaction.setBankId(update.getBankId());
            transaction.setBankTransactionId(update.getBankTransactionId());
            transactionRepository.save(transaction);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transaction));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<Transactions>>> getBySaleId(Long id) {
        List<Transactions> transactions = transactionRepository.getBySaleId(id);
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transactions));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }
}

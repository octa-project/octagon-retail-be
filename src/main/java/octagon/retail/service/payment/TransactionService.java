package octagon.retail.service.payment;

import octagon.retail.entity.payment.Transactions;
import octagon.retail.model.TransactionInvoiceModel;
import octagon.retail.model.TransactionModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.payment.TransactionRepository;
import octagon.retail.repository.payment.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<ResponseModel<Transactions>> saveTransaction(Transactions transaction) {
        Transactions transactions = transactionRepository.save(transaction);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, transactions));
    }

    // @Async
    // public ResponseEntity<ResponseModel<TransactionModel>>
    // sendTransactionByDate(Date startDate, Date endDate) {
    // var transactions = transactionRepository.getMany(startDate, endDate);
    // if (transactions == null || transactions.isEmpty())
    // return ResponseEntity.ok(new ResponseModel<>("500",
    // "Амжилтгүй. мэдээлэл олдсонгүй.", false, null));

    // var resp = new TransactionModel();

    // ///
    // resp.setTotalAmount(transactions.stream().mapToDouble(t ->
    // t.getAmount()).sum());
    // resp.setTotalAccountAmount(transactions.stream()
    // .filter(transaction -> transaction.getTransactionType() ==
    // TransactionType.account)
    // .mapToDouble(Transactions::getAmount)
    // .sum());
    // resp.setTotalCardAmount((transactions.stream()
    // .filter(transaction -> transaction.getTransactionType() ==
    // TransactionType.card)
    // .mapToDouble(Transactions::getAmount)
    // .sum()));
    // resp.setTotalCashAmount((transactions.stream()
    // .filter(transaction -> transaction.getTransactionType() ==
    // TransactionType.cash)
    // .mapToDouble(Transactions::getAmount)
    // .sum()));
    // resp.setTotalNonCashAmount((transactions.stream()
    // .filter(transaction -> transaction.getTransactionType() ==
    // TransactionType.nonCash)
    // .mapToDouble(Transactions::getAmount)
    // .sum()));
    // ///

    // Map<Date, Map<TransactionType, Long>> countByDateAndType =
    // transactions.stream()
    // .collect(Collectors.groupingBy(
    // i -> i.getDate(),
    // Collectors.groupingBy(
    // Transactions::getTransactionType,
    // Collectors.counting())));

    // List<TransactionInvoiceModel> datas = countByDateAndType.entrySet().stream()
    // .flatMap(dateCountEntry -> dateCountEntry.getValue().entrySet().stream()
    // .map(typeCountEntry -> {
    // TransactionInvoiceModel model = new TransactionInvoiceModel();
    // model.setDate(dateCountEntry.getKey());
    // model.setTransactionType(typeCountEntry.getKey());
    // model.setAmount(typeCountEntry.getValue().doubleValue());
    // model.setName("TransactionName");

    // return model;
    // }))
    // .collect(Collectors.toList());
    // resp.setData(datas);
    // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
    // resp));
    // }

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
            transaction.setSale(update.getSale());
            transaction.setTransactionName(update.getTransactionName());
            transaction.setAmount(update.getAmount());
            transaction.setBankTransactionId(update.getBankTransactionId());
            transaction.setBankTransactionId(update.getBankTransactionId());
            transactionRepository.save(transaction);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, transaction));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    // public ResponseEntity<ResponseModel<List<Transactions>>> getBySaleId(Long id)
    // {
    // List<Transactions> transactions = transactionRepository.getBySaleId(id);
    // if (!transactions.isEmpty()) {
    // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
    // transactions));
    // } else {
    // return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false,
    // null));
    // }
    // }
}

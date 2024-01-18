package octagon.retail.repository.payment;

import octagon.retail.entity.payment.BankTransactions;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BankTransactionRepository extends MainRepository<BankTransactions, Long> {

    @Query("select T from BankTransactions T where  T.date between :startDate and :endDate")
    List<BankTransactions> getMany(Date startDate, Date endDate);

    @Query("select T from BankTransactions T where T.saleId=:id")
    List<BankTransactions> getBySaleId(Long id);
}
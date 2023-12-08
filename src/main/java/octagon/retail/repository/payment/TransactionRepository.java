package octagon.retail.repository.payment;

import octagon.retail.entity.payment.Banks;
import octagon.retail.entity.payment.Transactions;
import octagon.retail.entity.sale.Sales;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MainRepository<Transactions, Long> {

    @Query("select T from Transactions T where T.isDeleted=false and T.date between :startDate and :endDate")
    List<Transactions> getMany(Date startDate, Date endDate);

    // @Query("select T from Transactions T where T.saleId=:sale")
    // List<Transactions> getBySaleId(Sales sale);
}
package octagon.retail.repository.payment;

import octagon.retail.entity.payment.Banks;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MainRepository<Banks, Long> {
    @Query("select b from Banks b where b.code = :code")
    Banks exist(String code);

    // @Query("select a from Sales a where a.isDeleted=false and a.date between
    // :startDate and :endDate")
    // List<Sales> getMany(Date startDate,Date endDate);
    Banks findByIsActiveTrue();
}
package octagon.retail.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import octagon.retail.entity.Purchase;

@Repository
public interface IPurchaseRepository extends MainRepository<Purchase, Long> {

    @Query("select a from Purchase a where a.date = :startDate")
    List<Purchase> getAllByStartDate(Date startDate);
}

package octagon.retail.repository.sale;

import octagon.retail.entity.sale.Sales;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends MainRepository<Sales, Long> {
    @Query("select a from Sales a where a.isDeleted=false and a.date between :startDate and :endDate")
    List<Sales> getMany(Date startDate,Date endDate);

}
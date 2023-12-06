package octagon.retail.repository;

import octagon.retail.entity.ItemPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPriceRepository extends MainRepository<ItemPrice, Long> {
    @Query("select ip from ItemPrice ip where ip.itemCodeId = :getItemCodeId")
    ItemPrice exist(Long getItemCodeId);
//    @Query("select a from Sales a where a.isDeleted=false and a.date between :startDate and :endDate")
//    List<Sales> getMany(Date startDate,Date endDate);

}
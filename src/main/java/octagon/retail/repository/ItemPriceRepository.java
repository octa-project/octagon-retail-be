package octagon.retail.repository;

import octagon.retail.entity.ItemPrices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPriceRepository extends MainRepository<ItemPrices, Long> {
    @Query("select ip from ItemPrices ip where ip.itemCodeId = :getItemCodeId")
    ItemPrices exist(Long getItemCodeId);
    // @Query("select ip from ItemPrices ip where ip.isDeleted=false and
    // ip.itemId=:id")
    // List<ItemPrices> getByItemId(Long id);

    List<ItemPrices> findAllByitemId(Long id);
}
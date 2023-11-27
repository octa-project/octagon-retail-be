package octagon.retail.repository;

import octagon.retail.entity.ItemPrices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemPriceRepository extends MainRepository<ItemPrices, Long>{
    @Query("select a from ItemPrices a where a.itemId = :itemId")
    List<ItemPrices> getItemPriceListByItemId(@Param("itemId") Long itemId);

}

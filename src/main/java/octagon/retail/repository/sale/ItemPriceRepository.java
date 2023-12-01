package octagon.retail.repository.sale;

import octagon.retail.entity.sale.ItemPrices;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemPriceRepository extends MainRepository<ItemPrices, Long> {
    @Query("select a from ItemPrices a where a.itemId = :itemId")
    List<ItemPrices> getItemPriceListByItemId(@Param("itemId") Long itemId);

}

package octagon.retail.repository;

import octagon.retail.entity.ItemPrices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemSearchRepository extends MainRepository<ItemPrices, Long> {
    @Query("select a from ItemPrices a where a.isDeleted=false and a.itemBarCode = :barcode")
    ItemPrices getOne(String barcode);
    @Query("select a from ItemPrices a where a.itemBarCode like :barcode%")
    List<ItemPrices> getLikeBarcode(String barcode);
    @Query("select a from ItemPrices a where a.itemName like %:name%")
    List<ItemPrices> getLikeName(String name);

    @Query("select a from ItemPrices a where cast(a.unitSalePrice as string) like :price%")
    List<ItemPrices> getLikePrice(String price);

}
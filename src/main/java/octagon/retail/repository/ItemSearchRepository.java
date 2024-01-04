package octagon.retail.repository;

import octagon.retail.entity.ItemCodes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemSearchRepository extends MainRepository<ItemCodes, Long> {
    @Query("select a from ItemCodes a where a.isDeleted=false and a.barcode = :barcode")
    ItemCodes getOne(String barcode);
    @Query("select a from ItemCodes a where a.barcode like :barcode%")
    List<ItemCodes> getLikeBarcode(String barcode);
    @Query("select a from ItemCodes a where LOWER(a.name) like %:name%")
    List<ItemCodes> getLikeName(String name);

    @Query("select a from ItemCodes a where cast(a.sellPrice as string) like :price%")
    List<ItemCodes> getLikePrice(String price);

}
package octagon.retail.repository;

import octagon.retail.entity.ItemCodes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemCodeRepository extends MainRepository<ItemCodes, Long>{
    @Query("select a from ItemCodes a where a.barcode = :barcode")
    ItemCodes getItemByBarcode(@Param("barcode") String barcode);

    @Query("SELECT a FROM ItemCodes a WHERE a.barcode LIKE :barcode%")
    List<ItemCodes> getItemsByCreatedByBarcodeIsLike(@Param("barcode") String barcode);
}

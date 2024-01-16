package octagon.retail.repository;

import octagon.retail.entity.ItemCodes;
import octagon.retail.model.item.CustomItemCodeModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IItemCodeRepository extends MainRepository<ItemCodes, Long> {
    @Query("select a from ItemCodes a where a.barcode = :barcode")
    Optional<ItemCodes> getItemByBarcode(@Param("barcode") String barcode);

    @Query("SELECT a FROM ItemCodes a WHERE a.barcode LIKE :barcode%")
    List<ItemCodes> getItemCodesByBarcodeIsLike(@Param("barcode") String barcode);

    @Query("select a from ItemCodes a where a.itemId = :id")
    List<ItemCodes> getItemCodesByItemId(@Param("id") Long id);

    List<ItemCodes> findByIdIn(List<Long> ids);

    @Query("select new octagon.retail.model.item.CustomItemCodeModel(a.id, a.itemId, a.barcode, a.name, a.sellPrice, a.costPrice, measure.name, measure.id, group.name,group.id,a.properQty,a.createdDate) "
            +
            "from ItemCodes a " +
            "left join ItemGroups group on a.itemGroupId = group.id " +
            "left join Measures measure on a.measureId = measure.id")
    List<CustomItemCodeModel> getCustomItemCodes();
}

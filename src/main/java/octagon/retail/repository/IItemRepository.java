package octagon.retail.repository;

import octagon.retail.entity.Items;
import octagon.retail.model.item.CompleteItemModel;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends MainRepository<Items, Long> {
    @Query("select a from Items a where a.code = :code")
    Items getItemByCode(@Param("code") String code);

    @Query("select new octagon.retail.model.item.CompleteItemModel(a.id, a.code, a.name, supplier.name, group.name, measure.name, a.isVat, a.isCityTax, a.isActive) "
            +
            "from Items a " +
            "left join Supplier supplier on a.supplierId = supplier.id " +
            "left join ItemGroups group on a.itemGroupId = group.id " +
            "left join Measures measure on a.measureId = measure.id")
    List<CompleteItemModel> getCompleteItemList();
}

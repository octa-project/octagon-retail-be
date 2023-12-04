package octagon.retail.repository;

import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.Measures;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IItemGroupRepository extends MainRepository<ItemGroups, Long>{
    @Query("select a from ItemGroups a where a.name = :name")
    ItemGroups getItemGroupsByName(@Param("name")String name);
}

package octagon.retail.repository;

import octagon.retail.entity.Items;
import octagon.retail.entity.Measures;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends MainRepository<Items, Long> {
    @Query("select a from Items a where a.code = :code")
    Items getItemByCode(@Param("code")String code);
}

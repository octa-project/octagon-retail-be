package octagon.retail.repository;

import octagon.retail.entity.Items;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends MainRepository<Items, Long> {
    @Query("select a from Items a where a.code = :code")
    Items getItemByCode(@Param("code") String code);
}

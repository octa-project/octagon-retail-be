package octagon.retail.repository;

import octagon.retail.entity.Measures;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMeasureRepository extends MainRepository<Measures, Long> {
    @Query("select a from Measures a where a.name = :name")
    Measures getMeasuresByName(@Param("name")String name);
}

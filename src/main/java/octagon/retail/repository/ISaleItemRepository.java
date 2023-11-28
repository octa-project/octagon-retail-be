package octagon.retail.repository;

import octagon.retail.entity.Items;
import octagon.retail.entity.SaleItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleItemRepository extends MainRepository<SaleItems, Long> {

    @Query("select a from SaleItems a where a.saleId = :saleId")
    List<SaleItems> getSaleItemsBySaleId(@Param("saleId") Long saleId);

    @Query("select a from SaleItems a where a.itemCodeId = :code")
    SaleItems getSaleItemByCode(@Param("code") String code);

}
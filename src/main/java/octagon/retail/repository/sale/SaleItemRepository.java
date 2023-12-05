package octagon.retail.repository.sale;

import octagon.retail.entity.sale.SaleItems;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends MainRepository<SaleItems, Long> {
    @Query("select a from SaleItems a where a.isDeleted = false and a.saleId = :sid")
    List<SaleItems> getSaleById(Long sid);
    @Query("select a from SaleItems a where a.saleId = :saleId and a.itemCodeId = :itemCodeId")
    SaleItems getSaleIdItemId(Long saleId,Long itemCodeId);
}
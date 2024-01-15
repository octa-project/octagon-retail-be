package octagon.retail.repository.sale;

import octagon.retail.entity.SaleItems;
import octagon.retail.model.sale.TopTenItems;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends MainRepository<SaleItems, Long> {
    // @Query("select a from SaleItems a where a.isDeleted = false and a.saleId =
    // :sid")
    // List<SaleItems> getSaleById(Long sid);

    List<SaleItems> findAllBySaleId(Long sid);

    @Query("select a from SaleItems a where a.saleId = :saleId and a.itemCodeId = :itemCodeId")
    SaleItems getSaleIdItemId(Long saleId, Long itemCodeId);

    // @Query("SELECT new octagon.retail.model.sale.TopTenItems(sum(s.qty) ,
    // s.itemBarCode ,s.itemName) FROM SaleItems s where s.isDeleted = false group
    // by s.itemBarCode , s.itemName order by sum(s.qty) desc limit 10")
    // List<TopTenItems> getTopTenItems();
}
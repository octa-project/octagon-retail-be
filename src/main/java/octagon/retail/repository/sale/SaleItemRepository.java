package octagon.retail.repository.sale;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.sale.SaleItems;
import octagon.retail.entity.sale.Sales;
import octagon.retail.repository.MainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleItemRepository extends MainRepository<SaleItems, Long> {
    List<SaleItems> getSaleById(Long sid);

    SaleItems getSaleItemsBySaleAndItemCode(Sales sale, ItemCodes itemCode);
}
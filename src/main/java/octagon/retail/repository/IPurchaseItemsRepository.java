package octagon.retail.repository;

import java.util.List;

import octagon.retail.entity.PurchaseItems;

public interface IPurchaseItemsRepository extends MainRepository<PurchaseItems, Long> {
    List<PurchaseItems> findByPurchaseId(Long id);
}

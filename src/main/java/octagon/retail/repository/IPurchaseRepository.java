package octagon.retail.repository;

import org.springframework.stereotype.Repository;

import octagon.retail.entity.Purchase;

@Repository
public interface IPurchaseRepository extends MainRepository<Purchase, Long> {
}

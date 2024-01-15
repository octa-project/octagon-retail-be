package octagon.retail.repository;

import java.util.Optional;

import octagon.retail.entity.Supplier;

public interface ICustomerSuppliersRepository extends MainRepository<Supplier, Long> {
    Optional<Supplier> findByCode(String code);
}

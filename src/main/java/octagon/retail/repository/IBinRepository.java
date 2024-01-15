package octagon.retail.repository;

import org.springframework.stereotype.Repository;

import octagon.retail.entity.Bin;

@Repository
public interface IBinRepository extends MainRepository<Bin, Long> {
}

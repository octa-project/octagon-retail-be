package octagon.retail.repository;

import jakarta.transaction.Transactional;
import octagon.retail.entity.Settings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingRepository extends MainRepository<Settings, Long> {

    @Modifying
    @Transactional
    @Query("update Settings s set s.name = :name, s.taxNumber = :taxNumber, s.address = :address, s.branchId = :branchId, s.isDeleted = :isDeleted where s.id = :id")
    void updateSettingById(
            Long id,
            String name,
            String taxNumber,
            String address,
            Long branchId,
            Boolean isDeleted
    );
}

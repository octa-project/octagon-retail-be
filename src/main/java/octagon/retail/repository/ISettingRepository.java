package octagon.retail.repository;

import jakarta.transaction.Transactional;
import octagon.retail.entity.Settings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingRepository extends MainRepository<Settings, Long> {


}

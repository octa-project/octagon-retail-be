package octagon.retail.repository;

import octagon.retail.entity.DeviceSetting;
import octagon.retail.entity.Settings;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceSettingRepository extends MainRepository<DeviceSetting, Long> {


}

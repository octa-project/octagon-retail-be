package octagon.retail.repository;

import octagon.retail.entity.DeviceSetting;
import octagon.retail.entity.ItemGroups;
import octagon.retail.entity.Settings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeviceSettingRepository extends MainRepository<DeviceSetting, Long> {

    @Query("select a from DeviceSetting a where a.branchId = :branchId and a.isCashierPrinter = true and a.isActive = true ")
    List<DeviceSetting> getDeviceSettingByBranch(@Param("branchId") Long branchId);

    @Query("select a from DeviceSetting a where a.branchId = :branchId and a.isCashierPrinter = false and a.isActive = true ")
    List<DeviceSetting> getDeviceSettingByBranchForOrder(@Param("branchId") Long branchId);


    @Query("select a from DeviceSetting a where a.branchId = :branchId and a.retailDeviceName = :deviceName and a.isCashierPrinter = true and a.isActive = true ")
    DeviceSetting getDeviceSettingForPrinter(@Param("branchId") Long branchId, @Param("deviceName") String deviceName );


}

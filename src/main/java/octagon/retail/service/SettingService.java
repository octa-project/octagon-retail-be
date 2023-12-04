package octagon.retail.service;

import octagon.retail.entity.DeviceSetting;
import octagon.retail.entity.PaymentSetting;
import octagon.retail.entity.Settings;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IDeviceSettingRepository;
import octagon.retail.repository.IPaymentSettingRepository;
import octagon.retail.repository.ISettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingService {

    @Autowired
    private ISettingRepository settingRepository;

    @Autowired
    private IDeviceSettingRepository deviceSettingRepository;

    @Autowired
    private IPaymentSettingRepository paymentSettingRepository;

    public ResponseEntity<ResponseModel<Settings>> saveSetting(Settings setting) {
        settingRepository.save(setting);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, setting));
    }

//    public ResponseEntity<ResponseModel<Settings>> updateSetting(Settings updatedSetting) {
//        Settings existingSetting = settingRepository.findById(updatedSetting.getId()).orElse(null);
//
//        if (existingSetting != null) {
//            existingSetting.setName(updatedSetting.getName());
//            existingSetting.setTaxNumber(updatedSetting.getTaxNumber());
//            existingSetting.setAddress(updatedSetting.getAddress());
//            existingSetting.setBranchId(updatedSetting.getBranchId());
//            existingSetting.setDeleted(updatedSetting.isDeleted());
//            settingRepository.save(existingSetting);
//
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingSetting));
//        }
//        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
//    }

    public ResponseEntity<ResponseModel<Settings>> getSettingById(Long settingId) {
        Settings setting = settingRepository.findById(settingId).orElse(null);
        if (setting != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, setting));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<Settings>>> getSettings() {
        List<Settings> settings = settingRepository.findAll();
        if (settings != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, settings));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Settings>> deleteSettingById(Long settingId) {
        settingRepository.deleteById(settingId);
        Settings setting = settingRepository.findById(settingId).orElse(null);
        if (setting == null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, setting));
    }

    public void insertDeviceSetting(DeviceSetting entity) {
        deviceSettingRepository.save(entity);
    }

    public Optional<DeviceSetting> getDeviceSettingById(Long id) {
        return deviceSettingRepository.findById(id);
    }

    public List<DeviceSetting> listOfDeviceId() {
       return deviceSettingRepository.findAll();
    }

    public void deleteDevice(DeviceSetting entity){
        deviceSettingRepository.delete(entity);
    }

    public DeviceSetting updateDevice(DeviceSetting entity){
        return deviceSettingRepository.save(entity);
    }

    public void insertPaymentSetting(PaymentSetting entity) {
        paymentSettingRepository.save(entity);
    }

    public Optional<PaymentSetting> getPaymenSettingById(Long id) {
        return paymentSettingRepository.findById(id);
    }

    public List<PaymentSetting> listOfPaymentMethod() {
        return paymentSettingRepository.findAll();
    }

    public void deletePaymentMethod(PaymentSetting entity){
        paymentSettingRepository.delete(entity);
    }

    public PaymentSetting updatePaymentMethod(PaymentSetting entity){
        return paymentSettingRepository.save(entity);
    }


}

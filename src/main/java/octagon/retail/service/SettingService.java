package octagon.retail.service;

import octagon.retail.entity.Settings;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ISettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    private ISettingRepository settingRepository;

    public ResponseEntity<ResponseModel<Settings>> saveSetting(Settings setting) {
        settingRepository.save(setting);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, setting));
    }

    public ResponseEntity<ResponseModel<Settings>> updateSetting(Settings updatedSetting) {
        Settings existingSetting = settingRepository.findById(updatedSetting.getId()).orElse(null);

        if (existingSetting != null) {
            existingSetting.setName(updatedSetting.getName());
            existingSetting.setTaxNumber(updatedSetting.getTaxNumber());
            existingSetting.setAddress(updatedSetting.getAddress());
            existingSetting.setBranchId(updatedSetting.getBranchId());
            existingSetting.setIsDeleted(updatedSetting.getIsDeleted());
            settingRepository.save(existingSetting);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingSetting));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

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
}

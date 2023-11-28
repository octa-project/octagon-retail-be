package octagon.retail.contoller;

import octagon.retail.entity.Settings;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setting")
@CrossOrigin(origins = "*")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @PostMapping("save-setting")
    public ResponseEntity<ResponseModel<Settings>> saveSettings(@RequestBody Settings setting) {
        return settingService.saveSetting(setting);
    }

//    @PostMapping("update-setting")
//    public ResponseEntity<ResponseModel<Settings>> updateSetting(@RequestBody Settings setting) {
//        return settingService.updateSetting(setting);
//    }

    @GetMapping("get-setting-by-id")
    public ResponseEntity<ResponseModel<Settings>> getSettingById(@RequestParam("id") Long id){
        return settingService.getSettingById(id);
    }

    @GetMapping("get-settings")
    public ResponseEntity<ResponseModel<List<Settings>>> getSettings(){
        return settingService.getSettings();
    }

    @DeleteMapping("delete-settings-by-id")
    public ResponseEntity<ResponseModel<Settings>> deleteSettingById(@RequestParam("id") Long id){
        return settingService.deleteSettingById(id);
    }

}

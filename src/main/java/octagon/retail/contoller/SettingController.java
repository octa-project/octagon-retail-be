package octagon.retail.contoller;

import jakarta.websocket.server.PathParam;
import octagon.retail.entity.DeviceSetting;
import octagon.retail.entity.Settings;
import octagon.retail.model.PrinterList;
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

    @PutMapping("save-setting")
    public ResponseEntity<ResponseModel<Settings>> updateSetting(@RequestBody Settings setting) {
        return settingService.updateSetting(setting);
    }

    @GetMapping("get-setting-by-id")
    public ResponseEntity<ResponseModel<Settings>> getSettingById(@PathParam("id") Long id){
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

    @GetMapping("get-printer-list")
    public ResponseEntity<ResponseModel<PrinterList>> getPrinterList(){
        return settingService.getPrinterList();
    }

    @PostMapping("device-settings")
    public ResponseEntity<ResponseModel<DeviceSetting>> insertDeviceSettings(@RequestBody DeviceSetting deviceSetting){
        return settingService.insertDeviceSettings(deviceSetting);
    }

    @PostMapping("device-settings-order")
    public ResponseEntity<ResponseModel<DeviceSetting>> insertDeviceSettingsForOrder(@RequestBody DeviceSetting deviceSetting){
        return settingService.insertDeviceSettingsForOrder(deviceSetting);
    }

    @PutMapping("device-settings")
    public ResponseEntity<ResponseModel<DeviceSetting>> updateDeviceSettings(@RequestBody DeviceSetting deviceSetting){
        return settingService.updateDeviceSettings(deviceSetting);
    }

    @DeleteMapping("device-settings")
    public ResponseEntity<ResponseModel<Boolean>> updateDeviceSettings(@RequestParam("id") Long id){
        return settingService.deleteDeviceSettings(id);
    }

    @GetMapping("device-settings")
    public ResponseEntity<ResponseModel<DeviceSetting>> insertDeviceSettings(@RequestParam("id") Long id, @RequestParam("deviceName") String deviceName){
        return settingService.getDeviceSettingForDeviceName(id, deviceName);
    }

    @GetMapping("device-settings-list")
    public ResponseEntity<ResponseModel<List<DeviceSetting>>> insertDeviceSettings(@RequestParam("id") Long id){
        return settingService.getAllByBranchId(id);
    }

    @GetMapping("device-settings-list-order")
    public ResponseEntity<ResponseModel<List<DeviceSetting>>> insertDeviceSettingsForOrder(@RequestParam("id") Long id){
        return settingService.getAllByBranchIdForOrder(id);
    }
}

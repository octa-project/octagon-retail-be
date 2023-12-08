package octagon.retail.service;

import com.google.gson.JsonElement;
import octagon.retail.entity.DeviceSetting;
import octagon.retail.entity.PaymentSetting;
import octagon.retail.entity.Settings;
import octagon.retail.model.PrinterList;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IDeviceSettingRepository;
import octagon.retail.repository.IPaymentSettingRepository;
import octagon.retail.repository.ISettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public ResponseEntity<ResponseModel<PrinterList>> getPrinterList() {


        WebClient webClient = WebClient.create();

        String apiUrl = "http://localhost:7000/printerConfig";

        // Create request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        try {

            String responseBody = webClient.get()
                    .uri(apiUrl)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // Blocking for simplicity, use subscribe() in a real application


            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            JsonElement jsonElement = jsonObject.get("data");
            List<String> printerNames = new ArrayList<>();

            if (!jsonElement.isJsonNull()) {
                for (JsonElement element : jsonElement.getAsJsonArray().asList()) {
                    printerNames.add(element.getAsString());
                }
            }

            PrinterList printerList = new PrinterList(printerNames);

            // Print the response body
            System.out.println("Response: " + responseBody);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, printerList));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseModel<>("500", "Error", true, null));

        }
    }

    public ResponseEntity<ResponseModel<DeviceSetting>> insertDeviceSettings(DeviceSetting deviceSetting) {
        insertDeviceSetting(deviceSetting);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, deviceSetting));
    }

    public ResponseEntity<ResponseModel<DeviceSetting>> updateDeviceSettings(DeviceSetting deviceSetting) {
        updateDevice(deviceSetting);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, deviceSetting));
    }

    public ResponseEntity<ResponseModel<Boolean>> deleteDeviceSettings(Long id) {
        Boolean result = deleteDevice(id);

        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", result, null));
    }


    public void insertDeviceSetting(DeviceSetting entity) {
        entity.setCreatedDate(LocalDateTime.now());
        deviceSettingRepository.save(entity);
    }

    public Optional<DeviceSetting> getDeviceSettingById(Long id) {
        return deviceSettingRepository.findById(id);
    }

    public List<DeviceSetting> listOfDeviceId() {
        return deviceSettingRepository.findAll();
    }

    public boolean deleteDevice(Long id) {
        Optional<DeviceSetting> optionalEntity = deviceSettingRepository.findById(id);

        if (optionalEntity.isPresent()) {
            DeviceSetting entity = optionalEntity.get();

            entity.setDeleted(true);
            entity.setModifiedDate(LocalDateTime.now());

            deviceSettingRepository.save(entity);
            return true;
        } else return false;
    }

    public DeviceSetting updateDevice(DeviceSetting entity) {
        entity.setModifiedDate(LocalDateTime.now());
        return deviceSettingRepository.save(entity);
    }

    public void insertPaymentSetting(PaymentSetting entity) {
        paymentSettingRepository.save(entity);
    }

    public Optional<PaymentSetting> getPaymentSettingById(Long id) {
        return paymentSettingRepository.findById(id);
    }

    public List<PaymentSetting> listOfPaymentMethod() {
        return paymentSettingRepository.findAll();
    }

    public void deletePaymentMethod(PaymentSetting entity) {
        paymentSettingRepository.delete(entity);
    }

    public PaymentSetting updatePaymentMethod(PaymentSetting entity) {
        return paymentSettingRepository.save(entity);
    }


}

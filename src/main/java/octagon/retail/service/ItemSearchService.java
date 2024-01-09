package octagon.retail.service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ItemSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSearchService {

    @Autowired
    private ItemSearchRepository itemSearchRepository;
    public ResponseEntity<ResponseModel<ItemCodes>> getOne(String itemBarCode) {
        ItemCodes price = itemSearchRepository.getOne(itemBarCode);
        if(price != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, price));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой барааны мэдээлэл олдсонгүй.".formatted(itemBarCode), false, null));
    }
    public ResponseEntity<ResponseModel<List<ItemCodes>>> getLikeBarcode(String itemBarCode) {
        List<ItemCodes> prices = itemSearchRepository.getLikeBarcode(itemBarCode);
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой барааны мэдээлэл олдсонгүй.".formatted(itemBarCode), false, null));
    }
    public ResponseEntity<ResponseModel<List<ItemCodes>>> getLikeName(String itemName) {
        List<ItemCodes> prices = itemSearchRepository.getLikeName(itemName.toLowerCase());
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s нэртэй барааны мэдээлэл олдсонгүй.".formatted(itemName), false, null));
    }
    public ResponseEntity<ResponseModel<List<ItemCodes>>> getLikePrice(String itemPrice) {
        List<ItemCodes> prices = itemSearchRepository.getLikePrice(itemPrice);
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s үнийн дүнтэй барааны мэдээлэл олдсонгүй.".formatted(itemPrice), false, null));
    }
}
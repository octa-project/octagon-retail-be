package octagon.retail.service;

import octagon.retail.entity.ItemPrices;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ItemSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemSearchService {

    @Autowired
    private ItemSearchRepository itemSearchRepository;
    public ResponseEntity<ResponseModel<ItemPrices>> getOne(String itemBarCode) {
        ItemPrices price = itemSearchRepository.getOne(itemBarCode);
        if(price != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, price));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой барааны мэдээлэл олдсонгүй.".formatted(itemBarCode), false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikeBarcode(String itemBarCode) {
        List<ItemPrices> prices = itemSearchRepository.getLikeBarcode(itemBarCode);
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой барааны мэдээлэл олдсонгүй.".formatted(itemBarCode), false, null));
    }


    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikeName(String itemName) {
        List<ItemPrices> prices = itemSearchRepository.getLikeName(itemName);
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s нэртэй барааны мэдээлэл олдсонгүй.".formatted(itemName), false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemPrices>>> getLikePrice(String itemPrice) {
        List<ItemPrices> prices = itemSearchRepository.getLikePrice(itemPrice);
        if(prices.size() > 0) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s үнийн дүнтэй барааны мэдээлэл олдсонгүй.".formatted(itemPrice), false, null));
    }
}
package octagon.retail.service;

import octagon.retail.entity.ItemPrice;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ItemPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPriceService {

    @Autowired
    private ItemPriceRepository itemPriceRepository;
    public ResponseEntity<ResponseModel<ItemPrice>> savePrice(ItemPrice price) {
        ItemPrice checkPrice = itemPriceRepository.exist(price.getItemCodeId());
        if(checkPrice == null) {
            ItemPrice prices = itemPriceRepository.save(price);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой барааны мэдээлэл бүртгэгдсэн байна.".formatted(price.getItemCodeId()), false, null));
    }
    public ResponseEntity<ResponseModel<ItemPrice>> updatePrice(ItemPrice update, Long id) {
        ItemPrice price = itemPriceRepository.findById(id).orElse(null);
        if (price != null) {
            price.setSellPrice(update.getSellPrice());
            price.setCostPrice(update.getCostPrice());
            price.setItemCodeId(update.getItemCodeId());
            itemPriceRepository.save(price);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, price));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }
    public ResponseEntity<ResponseModel<ItemPrice>> getOne(Long id) {
        ItemPrice prices = itemPriceRepository.findById(id).orElse(null);
        if(prices != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. %s кодтой банкны мэдээлэл олдсонгүй.".formatted(id), false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemPrice>>> getMany() {
        List<ItemPrice> prices = itemPriceRepository.findAll();
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, prices));
    }

    public ResponseEntity<ResponseModel<ItemPrice>> delete(Long id) {
        itemPriceRepository.deleteById(id);
        ItemPrice price = itemPriceRepository.findById(id).orElse(null);
        if (price == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа ахин оролдон уу", false, price));
    }
}
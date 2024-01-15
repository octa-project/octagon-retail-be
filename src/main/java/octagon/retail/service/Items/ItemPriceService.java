package octagon.retail.service.Items;

import octagon.retail.entity.ItemPrices;
import octagon.retail.repository.ItemPriceRepository;
import octagon.retail.response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPriceService {

    @Autowired
    private ItemPriceRepository itemPriceRepository;

    public ResponseEntity<ResponseModel<ItemPrices>> savePrice(ItemPrices price) {
        ItemPrices checkPrice = itemPriceRepository.exist(price.getItemCodeId());
        if (checkPrice == null) {
            ItemPrices prices = itemPriceRepository.save(price);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500",
                "Амжилтгүй. %s кодтой барааны мэдээлэл бүртгэгдсэн байна.".formatted(price.getItemCodeId()), false,
                null));
    }

    public ResponseEntity<ResponseModel<ItemPrices>> updatePrice(ItemPrices update, Long id) {
        ItemPrices price = itemPriceRepository.findById(id).orElse(null);
        if (price != null) {
            price.setUnitSalePrice(update.getUnitSalePrice());
            price.setUnitCostPrice(update.getUnitCostPrice());
            itemPriceRepository.save(price);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, price));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<ItemPrices>> getOne(Long id) {
        ItemPrices prices = itemPriceRepository.findById(id).orElse(null);
        if (prices != null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
        }
        return ResponseEntity.ok(new ResponseModel<>("500",
                "Амжилтгүй. %s кодтой банкны мэдээлэл олдсонгүй.".formatted(id), false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemPrices>>> getMany() {
        List<ItemPrices> prices = itemPriceRepository.findAll();
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, prices));
    }

    public ResponseEntity<ResponseModel<ItemPrices>> delete(Long id) {

        ItemPrices price = itemPriceRepository.findById(id).orElse(null);
        if (price != null) {
            price.setIsDeleted(true);
            itemPriceRepository.save(price);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, price));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй. Алдаа гарлаа дахин оролдон уу", false, null));
    }

    public ResponseEntity<ResponseModel<List<ItemPrices>>> getByItemId(Long id) {
        List<ItemPrices> prices = itemPriceRepository.findAllByitemId(id);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай бүргэгдлээ", true, prices));
    }

}
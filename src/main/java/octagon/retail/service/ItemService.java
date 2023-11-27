package octagon.retail.service;

import octagon.retail.entity.Items;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public ResponseEntity<ResponseModel<Items>> saveItem(Items item) {
        Items localItem = itemRepository.getItemByCode(item.getCode());
        if (localItem == null) {
            itemRepository.save(item);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, item));
        }
        return ResponseEntity.ok(new ResponseModel<>("404","Амжилтгүй - Өмнө нь бүртгэгдсэн бараа байна!",false,null));
    }
}

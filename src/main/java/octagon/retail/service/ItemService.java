package octagon.retail.service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.Items;
import octagon.retail.model.item.ItemCodeModel;
import octagon.retail.model.item.ItemModel;
import octagon.retail.model.item.ItemSaveModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.IItemGroupRepository;
import octagon.retail.repository.IItemRepository;
import octagon.retail.repository.IMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IItemCodeRepository itemCodeRepository;
    @Autowired
    private IMeasureRepository measureRepository;
    @Autowired
    private IItemGroupRepository itemGroupRepository;

    public ResponseEntity<ResponseModel<ItemSaveModel>> saveItem(ItemSaveModel item) {
        if (item.getItemgroupId() == null)
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - Барааны бүлэг байхгүй байна!", false, null));

        if (item.getMeasureId() == null)
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - Хэмжих нэгж байхгүй байна!", false, null));

        Items localItem = itemRepository.getItemByCode(item.getCode());
        if (localItem == null) {
            localItem = new Items();
            localItem.setCode(item.getCode());
            localItem.setName(item.getName());
            localItem.setItemgroupId(item.getItemgroupId());
            localItem.setMeasureId(item.getMeasureId());
            localItem.setBranchId(item.getBranchId());
            localItem.setIsActive(true);
            localItem.setIsDeleted(false);

            itemRepository.save(localItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, item));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - Өмнө нь бүртгэгдсэн бараа байна!", false, null));
    }

    public ResponseEntity<ResponseModel<ItemSaveModel>> updateItem(ItemSaveModel item) {
        if (item.getItemgroupId() == null)
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - Барааны бүлэг байхгүй байна!", false, null));

        if (item.getMeasureId() == null)
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - Хэмжих нэгж байхгүй байна!", false, null));
        
        Items existingItem = itemRepository.findById(item.getId()).orElse(null);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setCode(item.getCode());
            existingItem.setMeasureId(item.getMeasureId());
            existingItem.setItemgroupId(item.getItemgroupId());
            existingItem.setIsActive(item.getIsActive());
            existingItem.setIsDeleted(item.getIsDeleted());
            existingItem.setBranchId(item.getBranchId());

            itemRepository.save(existingItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, item));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    private ItemModel convertItemToModel(Items item) {
        var model = new ItemModel();
        model.setId(item.getId());
        model.setName(item.getName());
        model.setCode(item.getCode());
        model.setIsDeleted(item.getIsDeleted());
        model.setIsActive(item.getIsActive());
        model.setBranchId(item.getBranchId());
        var measure = measureRepository.findById(item.getMeasureId()).orElse(null);
        if (measure != null) {
            model.setMeasureId(measure.getId());
            model.setMeasureName(measure.getName());
        }
        var itemgroup = itemGroupRepository.findById(item.getItemgroupId()).orElse(null);
        if (itemgroup != null) {
            model.setItemgroupId(itemgroup.getId());
            model.setItemgroupName(itemgroup.getName());
        }
        model.setCreatedDate(item.getCreatedDate());

        var convertedItemCodes = item.getItemCodes().stream().map(this::convertItemCodeToModel).toList();
        model.setItemcodes(convertedItemCodes);
        return model;
    }

    private ItemCodeModel convertItemCodeToModel(ItemCodes itemCodes) {
        var model = new ItemCodeModel();
        model.setItemId(itemCodes.getItemId());
        model.setBarcode(itemCodes.getBarcode());
        model.setId(itemCodes.getId());
        model.setBranchid(itemCodes.getBranchId());
        model.setName(itemCodes.getName());
        model.setQty(itemCodes.getQty());
        model.setSellPrice(itemCodes.getSellPrice());
        return model;
    }

    public ResponseEntity<ResponseModel<ItemModel>> getItemById(Long itemId) {
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            var convertedItem = convertItemToModel(item);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", true, convertedItem));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<List<ItemModel>>> getAllItems() {
        List<Items> items = itemRepository.findAll();

        if (items == null || items.isEmpty())
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));

        var convertedItems = items.stream().map(this::convertItemToModel).toList();
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, convertedItems));
    }

    public ResponseEntity<ResponseModel<ItemModel>> getItemByCode(String code) {
        Items item = itemRepository.getItemByCode(code);
        if (item != null) {
            var convertTedItem = convertItemToModel(item);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, convertTedItem));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
        }
    }

    public ResponseEntity<ResponseModel<Items>> deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, item));
        }
    }
}

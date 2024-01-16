package octagon.retail.service.Items;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.Items;
import octagon.retail.model.item.CompleteItemModel;
import octagon.retail.model.item.ItemCodeModel;
import octagon.retail.model.item.ItemModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.IItemGroupRepository;
import octagon.retail.repository.IItemRepository;
import octagon.retail.repository.IMeasureRepository;
import octagon.retail.response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<ResponseModel<ItemModel>> updateItem(ItemModel item) {

        Items existingItem = itemRepository.findById(item.getId()).orElse(null);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setCode(item.getCode());
            existingItem.setMeasureId(item.getMeasureId());
            existingItem.setItemGroupId(item.getItemGroupId());
            existingItem.setIsActive(item.getIsActive());

            existingItem.setBranchId(item.getBranchId());

            itemRepository.save(existingItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    item));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false,
                null));
    }

    public ResponseEntity<ResponseModel<ItemModel>> saveItem(ItemModel item) {

        Items localItem = itemRepository.getItemByCode(item.getCode());
        if (localItem == null) {
            localItem = new Items();
            localItem.setCode(item.getCode());
            localItem.setName(item.getName());
            localItem.setItemGroupId(item.getItemGroupId());
            localItem.setMeasureId(item.getMeasureId());
            localItem.setBranchId(item.getBranchId());
            localItem.setIsActive(true);

            itemRepository.save(localItem);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    item));
        }
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй - Өмнө нь бүртгэгдсэн бараабайна!", false, null));

    }

    public ResponseEntity<ResponseModel<List<CompleteItemModel>>> getCompleteItemDatas() {
        try {
            var items = itemRepository.getCompleteItemList();
            if (items.isEmpty())
                return ResponseEntity.ok(new ResponseModel<>("500", "No Items", false,
                        null));
            List<CompleteItemModel> completeList = new ArrayList<>();
            for (CompleteItemModel completeItemModel : items) {
                var itemCode = itemCodeRepository.getItemCustomCodesByItemId(completeItemModel.getId());
                if (!itemCode.isEmpty()) {
                    completeItemModel.setChildren(itemCode);
                }
                completeList.add(completeItemModel);
            }

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    completeList));

        } catch (Exception e) {

            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }

    private ItemModel convertItemToModel(Items item) {
        var model = new ItemModel();
        model.setId(item.getId());
        model.setName(item.getName());
        model.setCode(item.getCode());
        model.setIsActive(item.getIsActive());
        // model.set(item.getBranchId());
        var measure = measureRepository.findById(item.getMeasureId()).orElse(null);
        if (measure != null) {
            model.setMeasureId(measure.getId());

        }
        var itemgroup = itemGroupRepository.findById(item.getItemGroupId()).orElse(null);
        if (itemgroup != null) {
            model.setItemGroupId(itemgroup.getId());

        }

        var itemCodes = itemCodeRepository.getItemCodesByItemId(item.getId());
        // var convertedItemCodes =
        // itemCodes.stream().map(this::convertItemCodeToModel).toList();
        // model.setItemCodes(convertedItemCodes);
        return model;
    }

    // private ItemCodeModel convertItemCodeToModel(ItemCodes itemCodes) {
    // var model = new ItemCodeModel();
    // model.setItemId(itemCodes.getItemId());
    // model.setBarcode(itemCodes.getBarcode());
    // model.setId(itemCodes.getId());
    // model.setBranchId(itemCodes.getBranchId());
    // model.setName(itemCodes.getName());
    // model.setMeasureId(itemCodes.getMeasureId());
    // // var measure =
    // // measureRepository.findById(itemCodes.getMeasureId()).orElse(null);
    // // if (measure != null) {
    // // model.setMeasureId(measure.getId());
    // // model.setMeasureName(measure.getName());
    // // }
    // model.setQty(itemCodes.getQty());
    // model.setSellPrice(itemCodes.getSellPrice());
    // model.setCostPrice(itemCodes.getCostPrice());
    // return model;
    // }

    public ResponseEntity<ResponseModel<ItemModel>> getItemById(Long itemId) {
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            var convertedItem = convertItemToModel(item);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилтгүй", true,
                    convertedItem));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false,
                    null));
        }
    }

    public ResponseEntity<ResponseModel<List<ItemModel>>> getAllItems() {
        List<Items> items = itemRepository.findAll();

        if (items == null || items.isEmpty())
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false,
                    null));

        var convertedItems = items.stream().map(this::convertItemToModel).toList();
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                convertedItems));
    }

    public ResponseEntity<ResponseModel<ItemModel>> getItemByCode(String code) {
        Items item = itemRepository.getItemByCode(code);
        if (item != null) {
            var convertTedItem = convertItemToModel(item);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    convertTedItem));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false,
                    null));
        }
    }

    public ResponseEntity<ResponseModel<Items>> deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
        Items item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    null));
        } else {
            return ResponseEntity
                    .ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу",
                            false, item));
        }
    }
}

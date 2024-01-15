// package octagon.retail.service;

// import java.util.List;

// import javax.swing.GroupLayout.Group;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import lombok.extern.log4j.Log4j2;
// import octagon.retail.entity.Bin;
// import octagon.retail.entity.Items;
// import octagon.retail.repository.IBinRepository;
// import octagon.retail.response.ResponseModel;
// import octagon.retail.service.Items.ItemService;

// @Log4j2
// @Service
// public class BinService {
// @Autowired
// IBinRepository binRepository;

// @Autowired
// ItemService itemService;

// public ResponseEntity<ResponseModel<List<Bin>>> getBinData() {
// try {
// var bins = binRepository.findAll();

// if (bins.isEmpty())
// return ResponseEntity.ok(new ResponseModel<>("500", "No bins", false,
// bins));
// return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", false,
// bins));
// } catch (Exception e) {
// log.info("getBinData:{}", e.getMessage());
// return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
// null));
// }
// }

// public Boolean MoveToBin(String entityName, Object object) {
// try {
// var bin = new Bin();
// bin.setEntitiyName(entityName);
// bin.setData(object);
// binRepository.save(bin);
// return true;
// } catch (Exception e) {
// log.info("{}/MoveToBin:{}", entityName, e.getMessage());
// return false;
// }
// }

// public ResponseEntity<ResponseModel<Object>> restoreData(Long id) {
// try {
// var bin = binRepository.findById(id).orElse(null);
// if (bin == null)
// return ResponseEntity.ok(new ResponseModel<>("500", "No bin", false,
// null));

// switch (bin.getEntitiyName()) {
// case "Item":
// Items item = bin.getData(Items.class);
// binRepository.delete(bin);
// itemService.saveItem(item);
// break;
// case "Group":
// ItemGroup group = bin.getData(ItemGroup.class);
// binRepository.delete(bin);
// groupService.addGroup(ItemGroupModel.convertToModel(group));
// break;
// case "Measure":
// Measure measure = bin.getData(Measure.class);
// binRepository.delete(bin);
// measureService.addMeasure(MeasureModel.convertToModel(measure));
// break;
// case "ItemCode":
// ItemCode itemCode = bin.getData(ItemCode.class);
// binRepository.delete(bin);
// itemCodeService.addItemCode(ItemCodeModel.convertToModel(itemCode));
// break;

// default:
// return ResponseEntity.ok(new ResponseModel<>("500", "Entity doesn't exist",
// false,
// null));
// }

// return ResponseEntity.ok(new ResponseModel<>("200", "Success", false,
// null));
// } catch (Exception e) {
// log.info("Id:{}/restoreItem:{}", id, e.getMessage());
// return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
// null));
// }
// }

// // public ResponseEntity<ResponseModel<ItemCode>> restoreItemCode(Long id) {
// // try {
// // var bin = binRepository.findById(id).orElse(null);
// // if (bin == null)
// // return ResponseEntity.ok(new ResponseModel<>("500", "No bin", false,
// // null));

// // ItemCode item = bin.getData(ItemCode.class);
// // binRepository.delete(bin);
// // var savedItem = itemCodeRepository.save(item);
// // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", false,
// // savedItem));

// // } catch (Exception e) {
// // log.info("Id:{}/restoreItemCode:{}", id, e.getMessage());
// // return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
// // null));
// // }
// // }

// // public ResponseEntity<ResponseModel<ItemGroup>> restoreItemGroup(Long id)
// {
// // try {
// // var bin = binRepository.findById(id).orElse(null);
// // if (bin == null)
// // return ResponseEntity.ok(new ResponseModel<>("500", "No bin", false,
// // null));

// // ItemGroup group = bin.getData(ItemGroup.class);
// // binRepository.delete(bin);
// // return groupService.addGroup(ItemGroupModel.convertToModel(group));

// // } catch (Exception e) {
// // log.info("Id:{}/restoreItemGroup:{}", id, e.getMessage());
// // return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
// // null));
// // }
// // }

// // public ResponseEntity<ResponseModel<Measure>> restoreMeasure(Long id) {
// // try {
// // var bin = binRepository.findById(id).orElse(null);
// // if (bin == null)
// // return ResponseEntity.ok(new ResponseModel<>("500", "No bin", false,
// // null));

// // Measure measure = bin.getData(Measure.class);
// // binRepository.delete(bin);
// // var savedMeasure = measureRepository.save(measure);
// // return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", false,
// // savedMeasure));

// // } catch (Exception e) {
// // log.info("Id:{}/restoreMeasure:{}", id, e.getMessage());
// // return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
// // null));
// // }
// // }
// }

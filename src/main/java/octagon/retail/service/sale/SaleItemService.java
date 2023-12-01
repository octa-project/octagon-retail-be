package octagon.retail.service.sale;

import octagon.retail.entity.sale.SaleItems;
import octagon.retail.entity.sale.Sales;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemRepository;
import octagon.retail.repository.sale.SaleItemRepository;
import octagon.retail.repository.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private IItemRepository itemRepository;

    public ResponseEntity<ResponseModel<SaleItems>> saveItem(SaleItems saleItem) {
        saleItemRepository.save(saleItem);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saleItem));
    }

    public ResponseEntity<ResponseModel<SaleItems>> updateItem(Long id, SaleItems update) {
        SaleItems items = saleItemRepository.findById(id).orElse(null);
        if (items != null) {
            items.setQty(update.getQty());
            items.setUnitSalePrice(update.getUnitSalePrice());
            items.setTotalSalePrice(update.getTotalSalePrice());
            saleItemRepository.save(items);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, items));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }


//
//    public ResponseEntity<ResponseModel<List<SaleItems>>> getSaleItemsBySaleId(Long saleId) {
//        List<SaleItems> saleItems = saleItemRepository.getSaleItemsBySaleId(saleId);
//        if (saleItems == null) {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
//        }
//        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saleItems));
//    }
//
//    public ResponseEntity<ResponseModel<List<SaleItems>>> getSaleItems() {
//        List<SaleItems> saleItems = saleItemRepository.findAll();
//        if (saleItems != null) {
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, saleItems));
//        } else {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
//        }
//    }

//    public ResponseEntity<ResponseModel<SaleItems>> addSaleItemToSale(String barcode, Long saleId) {
//        Sales localSale = saleRepository.findById(saleId).orElse(null);
//
//        if (localSale == null) {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Борлуулалт олдсонгүй", false, null));
//        }
//        List<SaleItems> localSaleItems = localSale.getSaleItems();
//        SaleItems newSaleItem = null;
//
//        Optional<SaleItems> existingSaleItemOptional = localSaleItems.stream()
//                .filter(saleItem -> barcode.equals(saleItem.getItemBarcode()))
//                .findFirst();
//
//        if (existingSaleItemOptional.isPresent()) {
//            SaleItems existingSaleItem = existingSaleItemOptional.get();
//            BigDecimal currentQty = existingSaleItem.getQty();
//            existingSaleItem.setQty(currentQty.add(BigDecimal.ONE));
//            saleItemRepository.save(existingSaleItem);
//
//            BigDecimal totalAmount = localSaleItems.stream()
//                    .map(saleItem -> saleItem.getQty().multiply(saleItem.getUnitSalePrice()))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//            localSale.setAmount(totalAmount);
//            saleRepository.save(localSale);
//            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingSaleItem));
//        } else {
//            Items item = itemRepository.getItemByBarcode(barcode);
//
//            if (item == null) {
//                return ResponseEntity.ok(new ResponseModel<>("500", "Бараа олдсонгүй", false, null));
//            } else {
//                newSaleItem = new SaleItems();
//                newSaleItem.setItemId(item.getId());
//                newSaleItem.setItemName(item.getName());
//                newSaleItem.setItemBarcode(item.getBarcode());
//                newSaleItem.setItemCode(item.getCode());
//                newSaleItem.setQty(BigDecimal.ONE);
//                newSaleItem.setUnitSalePrice(item.getSellPrice());
//                newSaleItem.setDeleted(false);
//                newSaleItem.setSaleId(saleId);
//
//                saleItemRepository.save(newSaleItem);
//                localSaleItems.add(newSaleItem);
//            }
//        }
//        BigDecimal totalAmount = localSaleItems.stream()
//                .map(saleItem -> saleItem.getQty().multiply(saleItem.getUnitSalePrice()))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        localSale.setAmount(totalAmount);
//        saleRepository.save(localSale);
//        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, newSaleItem));
//    }
//
//    public ResponseEntity<ResponseModel<Sales>> deleteSaleItemFromSale(String barcode, Long saleId) {
//        Sales sale = saleRepository.findById(saleId).orElse(null);
//
//        if (sale == null) {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
//        } else {
//            List<SaleItems> saleItems = sale.getSaleItems();
//            Optional<SaleItems> saleItemOptional = saleItems.stream()
//                    .filter(saleItem -> barcode.equals(saleItem.getItemBarcode()))
//                    .findFirst();
//
//            if (saleItemOptional.isPresent()) {
//                SaleItems saleItemToRemove = saleItemOptional.get();
//                saleItemToRemove.setDeleted(true);
//                saleItemToRemove.setSaleId(null);
//                saleItems.remove(saleItemToRemove);
//                saleItemRepository.delete(saleItemToRemove);
//
//                BigDecimal totalAmount = saleItems.stream()
//                        .map(saleItem -> saleItem.getQty().multiply(saleItem.getUnitSalePrice()))
//                        .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//                sale.setAmount(totalAmount);
//                saleRepository.save(sale);
//                return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
//            } else {
//                return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-той бараа борлуулалтаас олдсонгүй".formatted(barcode), false, null));
//            }
//        }
//    }
//

//
//
//    public ResponseEntity<ResponseModel<Sales>> updateSaleItemQtyFromSale(String barcode, BigDecimal qty, Long saleId) {
//        Sales sale = saleRepository.findById(saleId).orElse(null);
//        if (sale == null) {
//            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
//        } else {
//            List<SaleItems> saleItems = sale.getSaleItems();
//            Optional<SaleItems> saleItemOptional = saleItems.stream()
//                    .filter(saleItem -> barcode.equals(saleItem.getItemBarcode()))
//                    .findFirst();
//
//            if (saleItemOptional.isPresent()) {
//                SaleItems saleItemToUpdateQty = saleItemOptional.get();
//                saleItemToUpdateQty.setQty(qty);
//
//                BigDecimal totalAmount = saleItems.stream()
//                        .map(saleItem -> saleItem.getQty().multiply(saleItem.getUnitSalePrice()))
//                        .reduce(BigDecimal.ZERO, BigDecimal::add);
//                sale.setAmount(totalAmount);
//                saleRepository.save(sale);
//                return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
//            } else {
//                return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-той бараа борлуулалтаас олдсонгүй".formatted(barcode), false, null));
//            }
//        }
//    }
    public ResponseEntity<ResponseModel<Sales>> deleteAllBySaleId(Long saleId) {
        Sales sale = saleRepository.findById(saleId).orElse(null);

        if (sale == null) {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй %s-id тай борлуулалт олдсонгүй".formatted(saleId), false, null));
        } else {
            List<SaleItems> saleItems = sale.getSaleItems();
            saleItems.forEach(saleItem -> {
                saleItem.setIsDeleted(true);
            });
            saleItemRepository.saveAll(saleItems);
            saleItems.removeIf(SaleItems::isDeleted);
            sale.setSaleItems(saleItems);
            saleRepository.save(sale);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, sale));
        }
    }
    public ResponseEntity<ResponseModel<SaleItems>> deleteItem(Long itemId) {
        saleItemRepository.deleteById(itemId);
        SaleItems saleItem = saleItemRepository.findById(itemId).orElse(null);
        if (saleItem == null) {
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        } else {
            return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, saleItem));
        }
    }
}
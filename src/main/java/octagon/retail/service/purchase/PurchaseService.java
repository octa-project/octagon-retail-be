package octagon.retail.service.purchase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.ItemPrices;
import octagon.retail.entity.Purchase;
import octagon.retail.entity.PurchaseItems;
import octagon.retail.entity.Sales;
import octagon.retail.model.PurchaseModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.IItemCodeRepository;
import octagon.retail.repository.IPurchaseItemsRepository;
import octagon.retail.repository.IPurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    IPurchaseRepository purchaseRepository;

    @Autowired
    IItemCodeRepository itemCodeRepository;

    @Autowired
    IPurchaseItemsRepository purchaseItemsRepository;

    public ResponseEntity<ResponseModel<List<PurchaseModel>>> getAllByDate(String startDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date formatStartDate = dateFormat.parse(startDate);

            var purchases = purchaseRepository.getAllByStartDate(formatStartDate);
            if (purchases.isEmpty())
                return ResponseEntity.ok(new ResponseModel<>("500", "Service error: No Purchase",
                        false, null));
            var converted = purchases.stream().map(i -> convertToModel(i)).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, converted));
        } catch (ParseException e) {
            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false, null));
        }

    }

    public ResponseEntity<ResponseModel<Purchase>> savePurchase(PurchaseModel body) {
        try {
            // var purchase = buildPurchase(body);
            // if (purchase == null)
            // return ResponseEntity.ok(new ResponseModel<>("500", "Service error: ",
            // false, null));

            var purchase = new Purchase();

            for (var _itemModel : body.getPurchaseItems()) {
                var item = itemCodeRepository.getItemByBarcode(_itemModel.getBarcode()).orElse(null);

                if (item == null) {
                    return ResponseEntity
                            .ok(new ResponseModel<>("500", "Item is null", false, null));
                }
                item.setSellPrice(_itemModel.getSellPrice());
                item.setPurchasePrice(_itemModel.getCostPrice());
                itemCodeRepository.save(item);
                // totalAmount.add(_itemModel.getSellPrice());
                // totalCost.add(_itemModel.getCostPrice());
            }
            purchase.setTotalDiscount(body.getPurchaseItems().stream()
                    .map(PurchaseItems::getDiscount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            purchase.setDate(body.getDate());
            purchase.setTotalAmount(body.getPurchaseItems().stream()
                    .map(PurchaseItems::getSellPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            purchase.setTotalCost(body.getPurchaseItems().stream()
                    .map(PurchaseItems::getCostPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            purchase.setTotalQty(body.getPurchaseItems().stream()
                    .map(PurchaseItems::getQty)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
            purchase.setVat(body.getVat());
            purchase.setIsPaid(body.getIsPaid());
            purchase.setCityTax(body.getCityTax());
            purchase.setSupplierId(body.getSupplierId());

            var savedPurchase = purchaseRepository.save(purchase);

            List<PurchaseItems> purchaseItems = body.getPurchaseItems().stream()
                    .map(item -> {
                        item.setPurchaseId(savedPurchase.getId());
                        return item;
                    })
                    .collect(Collectors.toList());

            purchaseItemsRepository.saveAll(purchaseItems);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай хадгаллаа", true, savedPurchase));
        } catch (Exception e) {
            // Handle exceptions, e.g., database-related errors
            return ResponseEntity.ok(new ResponseModel<>("500", "Database error: " + e.getMessage(), false, null));
        }
    }

    private PurchaseModel convertToModel(Purchase purchase) {
        var model = new PurchaseModel();
        model.setTotalDiscount(purchase.getTotalDiscount());
        model.setCityTax(purchase.getCityTax());
        model.setDate(purchase.getDate());
        model.setId(purchase.getId());
        model.setIsPaid(purchase.getIsPaid());
        model.setSupplierId(purchase.getSupplierId());
        model.setTotalAmount(purchase.getTotalAmount());
        model.setTotalCost(purchase.getTotalCost());
        model.setTotalQty(purchase.getTotalQty());
        model.setVat(purchase.getVat());

        var items = purchaseItemsRepository.findByPurchaseId(purchase.getId());
        model.setPurchaseItems(items);
        return model;

    }

    public ResponseEntity<ResponseModel<List<PurchaseModel>>> getAllPurchases() {
        try {
            var purchases = purchaseRepository.findAll();
            if (purchases.isEmpty())
                return ResponseEntity.ok(new ResponseModel<>("500", "Service error: No Purchase",
                        false, null));
            var converted = purchases.stream().map(i -> convertToModel(i)).collect(Collectors.toList());

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, converted));
        } catch (Exception e) {
            // Handle exceptions, e.g., database-related errors
            return ResponseEntity.ok(new ResponseModel<>("500", "Database error: " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ResponseModel<PurchaseModel>> getPurchase(Long id) {
        try {
            var purchase = purchaseRepository.findById(id).orElse(null);

            if (purchase == null)
                return ResponseEntity.ok(new ResponseModel<>("500", "Service error: ",
                        false, null));
            var model = convertToModel(purchase);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, model));
        } catch (Exception e) {
            // Handle exceptions, e.g., database-related errors
            return ResponseEntity.ok(new ResponseModel<>("500", "Database error: " + e.getMessage(), false, null));
        }
    }
}

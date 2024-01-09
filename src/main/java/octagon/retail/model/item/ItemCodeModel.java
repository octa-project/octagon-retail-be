package octagon.retail.model.item;

import lombok.Data;
import octagon.retail.entity.ItemCodes;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

@Data
public class ItemCodeModel {
    @NotNull(message = "id is null")
    private Long id;
    @NotNull(message = "itemId is null")
    private Long itemId;
    private String barcode;
    private String name;
    @NotNull(message = "sellPrice is null")
    private BigDecimal sellPrice;
    @NotNull(message = "purchasePrice is null")
    private BigDecimal purchasePrice;
    private Long measureId;
    private String measureName;
    @NotNull(message = "qty is null")
    private BigDecimal qty;
    private Boolean isDeleted;
    private Long branchid;

    // public static ItemCodeModel convertToModel(ItemCodes itemCodes) {
    // var model = new ItemCodeModel();
    // model.setBarcode(itemCodes.getBarcode());
    // model.setId(itemCodes.getId());
    // model.setItemId(itemCodes.getItemId());
    // model.setName(itemCodes.getName());
    // model.setSellPrice(itemCodes.getSellPrice());
    // model.setPurchasePrice(itemCodes.getPurchasePrice());
    // model.setMeasureId(itemCodes.getMeasureId());
    // // model.set
    // }
}

package octagon.retail.model.item;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import octagon.retail.entity.ItemCodes;

@Data
@Validated
public class ItemCodeModel {

    @JsonProperty(value = "id")
    private Long id;

    // @NotNull(message = "itemId is null")
    @JsonProperty(value = "itemId")
    private Long itemId;

    @NotNull(message = "barcode is null")
    @JsonProperty(value = "barcode")
    private String barcode;

    @NotNull(message = "name is null")
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message = "sellPrice is null")
    @JsonProperty(value = "sellPrice")
    private BigDecimal sellPrice;

    @NotNull(message = "costPrice is null")
    @JsonProperty(value = "costPrice")
    private BigDecimal costPrice;

    @JsonProperty(value = "expirationId")
    private Long expirationId;

    @NotNull(message = "measureId is null")
    @JsonProperty(value = "measureId")
    private Long measureId;

    @JsonProperty(value = "qty")
    private BigDecimal qty;

    @JsonProperty(value = "properQty")
    private BigDecimal properQty;

    public ItemCodeModel(String barcode, String name,
            BigDecimal sellPrice,
            BigDecimal costPrice,
            BigDecimal qty,
            BigDecimal properQty) {

        this.barcode = barcode;
        this.costPrice = costPrice;
        this.name = name;
        this.qty = qty;
        this.sellPrice = sellPrice;
        this.properQty = properQty;

    }

    public static ItemCodes convert(ItemCodes itemCode, ItemCodeModel model) {
        if (itemCode == null)
            itemCode = new ItemCodes();
        itemCode.setBarcode(model.getBarcode());
        itemCode.setCostPrice(model.getCostPrice());
        itemCode.setItemId(model.getItemId());
        itemCode.setMeasureId(model.getMeasureId());
        itemCode.setQty(model.getQty());
        itemCode.setProperQty(model.getProperQty());
        itemCode.setExpirationId(model.getExpirationId());
        itemCode.setName(model.getName());
        itemCode.setSellPrice(model.getSellPrice());

        return itemCode;
    }

    public static ItemCodeModel convertToModel(ItemCodes itemCode) {
        var model = new ItemCodeModel(itemCode.getBarcode(), itemCode.getName(), itemCode.getSellPrice(),
                itemCode.getCostPrice(), itemCode.getQty(), itemCode.getProperQty());

        return model;
    }
}

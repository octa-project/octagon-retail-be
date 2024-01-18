package octagon.retail.model.item;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import octagon.retail.entity.Items;

@Data
@Validated
public class ItemModel {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "branchId")
    private Long branchId;

    @NotNull(message = "code is null")
    @JsonProperty(value = "code")
    private String code;

    @NotNull(message = "vatCode is null")
    @JsonProperty(value = "vatCode")
    private String vatCode;

    @NotNull(message = "name is null")
    @JsonProperty(value = "name")
    private String name;

    @NotNull(message = "supplierId is null")
    @JsonProperty(value = "supplierId")
    private Long supplierId;

    @NotNull(message = "measureId is null")
    @JsonProperty(value = "measureId")
    private Long measureId;

    @NotNull(message = "itemGroupId is null")
    @JsonProperty(value = "itemGroupId")
    private Long itemGroupId;

    // @NotNull(message = "isVat is null")
    @JsonProperty(value = "isVat")
    private Boolean isVat;

    // @NotNull(message = "isCityTax is null")
    @JsonProperty(value = "isCityTax")
    private Boolean isCityTax;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "isComplete")
    private Boolean isComplete;

    private List<ItemCodeModel> itemCodes;

    public static Items convert(Items item, ItemModel model) {
        if (item == null)
            item = new Items();
        item.setCode(model.getCode());
        item.setSupplierId(model.getSupplierId());
        item.setIsActive(model.getIsActive());
        item.setIsCityTax(model.getIsCityTax());
        item.setIsComplete(model.getIsComplete());
        item.setIsVat(model.getIsVat());
        item.setItemGroupId(model.getItemGroupId());
        item.setMeasureId(model.getMeasureId());
        item.setName(model.getName());
        item.setVatCode(model.getVatCode());
        return item;
    }

    public static ItemModel convertToModel(Items item) {
        var itemModel = new ItemModel();
        itemModel.setCode(item.getCode());
        itemModel.setSupplierId(item.getSupplierId());
        itemModel.setIsActive(item.getIsActive());
        itemModel.setIsCityTax(item.getIsCityTax());
        itemModel.setIsComplete(item.getIsComplete());
        itemModel.setIsVat(item.getIsVat());
        itemModel.setItemGroupId(item.getItemGroupId());
        itemModel.setMeasureId(item.getMeasureId());
        itemModel.setName(item.getName());
        itemModel.setVatCode(item.getVatCode());
        return itemModel;
    }
}

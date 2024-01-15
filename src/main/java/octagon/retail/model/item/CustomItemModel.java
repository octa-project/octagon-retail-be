package octagon.retail.model.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomItemModel {
    @JsonProperty(value = "id")
    private Long id;

    @NotNull(message = "code is null")
    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "vatCode")
    private String vatCode;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "customerId")
    private Long customerId;

    @JsonProperty(value = "customerName")
    private String customerName;

    @JsonProperty(value = "measureId")
    private Long measureId;

    @JsonProperty(value = "measureName")
    private String measureName;

    @JsonProperty(value = "itemGroupId")
    private Long itemGroupId;

    @JsonProperty(value = "itemGroupName")
    private String itemGroupName;

    @JsonProperty(value = "isVat")
    private Boolean isVat;

    @JsonProperty(value = "isCityTax")
    private Boolean isCityTax;

    // @JsonProperty(value = "isActive")
    // private Boolean isActive;

    // @JsonProperty(value = "isComplete")
    // private Boolean isComplete;

    public CustomItemModel(Long id, String code, String vatCode, String name, Long customerId, String customerName,
            Long measureId,
            String measureName, Long itemGroupId, String itemGroupName,
            Boolean isVat, Boolean isCityTax) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.vatCode = vatCode;
        this.isCityTax = isCityTax;
        this.isVat = isVat;
        this.customerId = customerId;
        this.customerName = customerName;
        this.itemGroupId = itemGroupId;
        this.itemGroupName = itemGroupName;
        this.measureId = measureId;
        this.measureName = measureName;

    }
}

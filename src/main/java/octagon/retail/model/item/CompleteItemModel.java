package octagon.retail.model.item;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompleteItemModel {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "vatCode")
    private String vatCode;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "customer")
    private String customer;

    @JsonProperty(value = "itemGroupName")
    private String itemGroupName;

    @JsonProperty(value = "measureName")
    private String measureName;

    @JsonProperty(value = "children")
    private List<ItemCodeModel> children;

    @JsonProperty(value = "isVat")
    private Boolean isVat;

    @JsonProperty(value = "isCityTax")
    private Boolean isCityTax;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    public CompleteItemModel(Long id, String code, String name, String customer, String groupName, String measureName,

            Boolean isVat, Boolean isCityTax, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.isCityTax = isCityTax;
        this.isActive = isActive;
        this.measureName = measureName;
        this.itemGroupName = groupName;
        this.name = name;
        this.isVat = isVat;
    }
}

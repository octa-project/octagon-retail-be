package octagon.retail.model.item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomItemCodeModel {
    @JsonProperty(value = "id")
    private Long id;

    @NotNull(message = "itemId is null")
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

    @NotNull(message = "measureName is null")
    @JsonProperty(value = "measureName")
    private String measureName;

    @NotNull(message = "measureId is null")
    @JsonProperty(value = "measureId")
    private Long measureId;

    @NotNull(message = "groupName is null")
    @JsonProperty(value = "groupName")
    private String groupName;

    @NotNull(message = "groupId is null")
    @JsonProperty(value = "groupId")
    private Long groupId;

    @NotNull(message = "properQty is null")
    @JsonProperty(value = "properQty")
    private BigDecimal properQty;

    @NotNull(message = "qty is null")
    @JsonProperty(value = "qty")
    private BigDecimal qty;

    @JsonProperty(value = "createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    public CustomItemCodeModel(Long id, Long itemId, String barcode, String name,
            BigDecimal sellPrice,
            BigDecimal costPrice,
            String measureName, Long measureId, String groupName, Long groupId,
            BigDecimal properQty, BigDecimal qty,
            LocalDateTime createdDate) {
        this.id = id;
        this.itemId = itemId;
        this.barcode = barcode;
        this.name = name;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
        this.properQty = properQty;
        this.qty = qty;
        this.measureId = measureId;
        this.measureName = measureName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.createdDate = createdDate;
    }
}

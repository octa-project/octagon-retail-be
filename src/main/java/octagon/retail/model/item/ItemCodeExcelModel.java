package octagon.retail.model.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemCodeExcelModel {

    private String barcode;

    private String name;

    private Double sellPrice;

    private Double costPrice;

    private Long expirationId;

    private String measureCode;

    private String internalCode;

    private Double packSize;

    private Double qty;

    private Double properQty;
}

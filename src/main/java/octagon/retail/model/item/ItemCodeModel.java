package octagon.retail.model.item;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemCodeModel {
    private Long id;
    private Long itemId;
    private String barcode;
    private String name;
    private BigDecimal sellPrice;
    private Long measureId;
    private BigDecimal qty;
    private Boolean isDeleted;
    private Long branchid;

}

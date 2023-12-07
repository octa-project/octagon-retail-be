package octagon.retail.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemCodeModel {
    private Long id;
    private Long itemId;
    private String name;
    private BigDecimal sellPrice;
    private Long measureId;
    private BigDecimal qty;
    private Boolean isDeleted;
    private Long branchid;

}

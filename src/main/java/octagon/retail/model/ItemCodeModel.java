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

    public ItemCodeModel(
            Long id,
            Long itemId,
            String name,
            BigDecimal sellPrice,
            Long measureId,
            BigDecimal qty,
            Boolean isDeleted,
            Long branchid
    ){
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.sellPrice = sellPrice;
        this.measureId = measureId;
        this.qty = qty;
        this.isDeleted = isDeleted;
        this.branchid = branchid;
    }
}

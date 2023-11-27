package octagon.retail.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.PrimitiveIterator;

@Data
public class ItemPriceModel {
    private Long id;
    private Long itemId;
    private Long itemCodeId;
    private BigDecimal sellPrice;
    private Date dateTime;
    private Boolean isDeleted;
    private Long branchId;

    public ItemPriceModel(
            Long id,
            Long itemId,
            Long itemCodeId,
            BigDecimal sellPrice,
            Date dateTime,
            Boolean isDeleted,
            Long branchId
    ){
        this.id = id;
        this.itemId = itemId;
        this.itemCodeId = itemCodeId;
        this.sellPrice = sellPrice;
        this.dateTime = dateTime;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

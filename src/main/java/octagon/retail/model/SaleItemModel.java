package octagon.retail.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemModel {
    
    private Long id;
    private Long saleId;
    private Long itemId;
    private String itemCodeId;
    private BigDecimal qty;
    private BigDecimal unitSalePrice;
    private BigDecimal totalSalePrice;
    private Boolean isDeleted;
    private Long branchId;

    public SaleItemModel(Long id, Long saleId, Long itemId, String itemCodeId, BigDecimal qty, BigDecimal unitSalePrice, BigDecimal totalSalePrice, Boolean isDeleted, Long branchId) {
        this.id = id;
        this.saleId = saleId;
        this.itemId = itemId;
        this.itemCodeId = itemCodeId;
        this.qty = qty;
        this.unitSalePrice = unitSalePrice;
        this.totalSalePrice = totalSalePrice;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}


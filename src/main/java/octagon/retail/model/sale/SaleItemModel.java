package octagon.retail.model.sale;

import lombok.Data;
import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.Items;
import octagon.retail.model.ItemCodeModel;
import octagon.retail.model.ItemModel;

import java.math.BigDecimal;

@Data
public class SaleItemModel {

    private Long id;
    private Long saleId;
    private String barcode;
    private ItemCodes itemCodeId;
    private BigDecimal qty;
    private BigDecimal unitSalePrice;
    private BigDecimal totalSalePrice;
    private Boolean isDeleted;
    private Long branchId;
}

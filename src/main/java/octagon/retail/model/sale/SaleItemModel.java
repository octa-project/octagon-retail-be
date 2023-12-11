package octagon.retail.model.sale;

import lombok.Data;
import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.Items;
import octagon.retail.entity.sale.SaleItems;
import octagon.retail.model.ItemCodeModel;
import octagon.retail.model.ItemModel;

import java.math.BigDecimal;

@Data
public class SaleItemModel {

    private Long id;
    private Long saleId;
    private String barcode;
    private Long itemCodeId;
    private BigDecimal qty;
    private BigDecimal unitSalePrice;
    private BigDecimal totalSalePrice;
    private Boolean isDeleted;
    private Long branchId;

    public static SaleItems convert(SaleItems saleItems, SaleItemModel model) {
        if (saleItems == null) {
            saleItems = new SaleItems();
        }
        saleItems.setBranchId(model.getBranchId());
        saleItems.setId(model.getId());
        // saleItems.setItemCode(model.getItemCodeId());
        saleItems.setQty(model.getQty());
        saleItems.setTotalSalePrice(model.getTotalSalePrice());
        saleItems.setUnitSalePrice(model.getUnitSalePrice());
        return saleItems;
    }
}

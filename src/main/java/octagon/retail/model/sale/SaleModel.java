package octagon.retail.model.sale;

import lombok.Data;
import octagon.retail.entity.SaleItems;
import octagon.retail.entity.Sales;
import octagon.retail.utils.SaleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class SaleModel {
    private Long id;
    private BigDecimal totalQty;
    private BigDecimal totalAmount;
    private Date date;
    private BigDecimal paidTotalAmount;
    private Boolean isPaid;
    private Long branchId;
    private List<SaleItems> stock;

    public static Sales convert(Sales sale, SaleModel model, SaleType type) {
        if (sale == null) {
            sale = new Sales();
        }
        sale.setBranchId(model.getBranchId());
        sale.setId(model.getId());
        sale.setIsPaid(model.getIsPaid());
        sale.setTotalAmount(model.getTotalAmount());
        sale.setTotalPaidAmount(model.getPaidTotalAmount());
        sale.setTotalQty(model.getTotalQty());
        sale.setType(type);
        return sale;
    }
}
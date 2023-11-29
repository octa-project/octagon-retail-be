package octagon.retail.model;

import lombok.Data;
import octagon.retail.entity.SaleItems;
import octagon.retail.entity.Transactions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class  SaleModel {
    private Long id;
    private Date date;
    private BigDecimal totalQty;
    private BigDecimal totalAmount;
    private BigDecimal paidTotalAmount;
    private Boolean isPaid;
    private Boolean isDeleted;
    private Long branchId;
    private Long createdUserId;
    private List<SaleItems> saleItems;
    private List<Transactions> transactions;

    public SaleModel(Long id, Date date, BigDecimal totalQty, BigDecimal totalAmount, BigDecimal paidTotalAmount, Boolean isPaid, Boolean isDeleted, Long branchId, Long createdUserId, List<SaleItems> saleItems, List<Transactions> transactions) {
        this.id = id;
        this.date = date;
        this.totalQty = totalQty;
        this.totalAmount = totalAmount;
        this.paidTotalAmount = paidTotalAmount;
        this.isPaid = isPaid;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
        this.createdUserId = createdUserId;
        this.saleItems = saleItems;
        this.transactions = transactions;
    }
}

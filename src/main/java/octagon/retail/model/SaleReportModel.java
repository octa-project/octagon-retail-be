package octagon.retail.model;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class SaleReportModel {
    private Long id;
    private Date date;
    private String barcode;
    private String name;
    private BigDecimal qty;
    private BigDecimal itemPrice;
    private BigDecimal discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private BigDecimal totalVatAmount;
    private BigDecimal totalPaidAmount;
    private BigDecimal bonusAmount;
    private BigDecimal bonusUsedAmount;
    public SaleReportModel() {
    }
    public SaleReportModel(
            Object date,
            String barcode,
            String name,
            BigDecimal qty,
            BigDecimal itemPrice,
            BigDecimal totalAmount,
            BigDecimal totalPaidAmount
    ) {
        this.date = (Date) date;
        this.barcode = barcode;
        this.name = name;
        this.qty = qty;
        this.itemPrice = itemPrice;
        this.totalAmount = totalAmount;
        this.totalVatAmount = BigDecimal.ZERO;
        this.discountPercent = BigDecimal.ZERO;
        this.discountAmount = BigDecimal.ZERO;
        this.totalPaidAmount = totalPaidAmount;
        this.bonusAmount = BigDecimal.ZERO;
        this.bonusUsedAmount = BigDecimal.ZERO;
    }
}
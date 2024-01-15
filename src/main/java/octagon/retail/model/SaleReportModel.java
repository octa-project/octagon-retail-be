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
    private String itemGroup;
    private BigDecimal qty;
    private BigDecimal itemPrice;
    private BigDecimal profit;
    private BigDecimal itemDiscount;
    private BigDecimal cardDiscount;
    private BigDecimal totalAmount;
    private Integer vat;
    private Integer cityTax;
    private Integer payment;
    private Integer total;
    private String user;
    private Long createdUserId;
    private Long branchId;

    public SaleReportModel(
            Long id,
            Date date,
            String barcode,
            String name,
            String itemGroup,
            BigDecimal qty,
            BigDecimal itemPrice,
            BigDecimal profit,
            BigDecimal itemDiscount,
            BigDecimal cardDiscount,
            BigDecimal totalAmount,
            Integer vat,
            Integer cityTax,
            Integer payment,
            Integer total,
            String user
    ) {
        this.id = id;
        this.date = date;
        this.barcode = barcode;
        this.name = name;
        this.itemGroup = itemGroup;
        this.qty = qty;
        this.itemPrice = itemPrice;
        this.profit = profit;
        this.itemDiscount = itemDiscount;
        this.cardDiscount = cardDiscount;
        this.totalAmount = totalAmount;
        this.vat = vat;
        this.cityTax = cityTax;
        this.payment = payment;
        this.total = total;
        this.user = user;
    }
}

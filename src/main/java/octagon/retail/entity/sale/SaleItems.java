package octagon.retail.entity.sale;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import org.springframework.data.relational.core.sql.In;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_items")
public class SaleItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saleId")
    private Long saleId;

    @Column(name = "itemId")
    private Long itemId;

    @Column(name = "itemCodeId")
    private Long itemCodeId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "itemGroup")
    private String itemGroup;

    @Column(name = "item_bar_code")
    private String itemBarCode;

    @Column(name = "profit")
    private BigDecimal profit;

    @Column(name = "itemDiscount")
    private BigDecimal itemDiscount;

    @Column(name = "cardDiscount")
    private BigDecimal cardDiscount;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "vat")
    private Integer vat;

    @Column(name = "cityTax")
    private Integer cityTax;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "total")
    private Integer total;

    @Column(name = "unit_sale_price")
    private BigDecimal unitSalePrice;

    @Column(name = "total_sale_price")
    private BigDecimal totalSalePrice;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @Column(name = "branchId")
    private Long branchId;

}

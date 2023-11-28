package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_items")
public class SaleItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_code_id")
    private Long itemCodeId;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "unit_sale_price")
    private BigDecimal unitSalePrice;

    @Column(name = "total_sale_price")
    private BigDecimal totalSalePrice;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "branch_id")
    private Long branchId;

}

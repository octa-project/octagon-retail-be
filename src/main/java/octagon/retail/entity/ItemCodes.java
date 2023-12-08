package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import octagon.retail.entity.sale.SaleItems;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_codes")
public class ItemCodes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "branch_id")
    private Long branchId;

    @JsonIgnore
    @OneToOne
    private SaleItems saleItem;
}

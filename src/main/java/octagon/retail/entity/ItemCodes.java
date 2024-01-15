package octagon.retail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item_code")
public class ItemCodes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "expiration_id")
    private Long expirationId;

    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "item_group_id")
    private Long itemGroupId;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "proper_qty")
    private BigDecimal properQty;

    @Column(name = "pack_size")
    private BigDecimal packSize;
}

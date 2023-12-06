package octagon.retail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @NotNull
    @Column(name = "item_id")
    private Long itemId;
    @NotNull
    @Column(name = "barcode")
    private String barcode;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "sell_price")
    private BigDecimal sellPrice;
    @NotNull
    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @NotNull
    @Column(name = "branch_id")
    private Long branchId;
}

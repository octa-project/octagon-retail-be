package octagon.retail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_items")
public class SaleItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long saleId;

    @NotNull
    private Long itemId;

    @NotNull
    private Long itemCodeId;

    @NotNull
    private String itemName;

    @NotNull
    private String itemBarCode;

    @NotNull
    private BigDecimal qty;

    @NotNull
    private BigDecimal unitSalePrice;

    private BigDecimal totalSalePrice;

    private Boolean isDeleted = false;

    private Long branchId;

}

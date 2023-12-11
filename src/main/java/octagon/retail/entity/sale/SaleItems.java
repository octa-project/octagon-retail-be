package octagon.retail.entity.sale;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import octagon.retail.entity.ItemCodes;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saleId")
    private Sales sale;

    @OneToOne
    @JoinColumn(name = "item_codes_id")
    private ItemCodes itemCode;

    @NotNull
    private BigDecimal qty;

    @NotNull
    private BigDecimal unitSalePrice;

    private BigDecimal totalSalePrice;

    private Boolean isDeleted = false;

    private Long branchId;

}

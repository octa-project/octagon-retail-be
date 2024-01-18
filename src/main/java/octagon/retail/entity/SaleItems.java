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

    private Long saleId;

    private Long itemId;

    private Long itemCodeId;

    private String itemName;

    private String itemBarcode;

    private BigDecimal qty;

    private BigDecimal unitSalePrice;

    private BigDecimal totalSalePrice;

}

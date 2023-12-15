package octagon.retail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_prices")
public class ItemPrices extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

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

    @NotNull
    private BigDecimal unitCostPrice;

    private Boolean isDeleted=false;

    private Long branchId;

}

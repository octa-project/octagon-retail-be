package octagon.retail.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import octagon.retail.entity.PurchaseItems;

@Data
public class PurchaseModel {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private Long branchId;

    private BigDecimal totalAmount;

    private BigDecimal totalQty;

    private BigDecimal totalCost;

    private BigDecimal vat;

    private BigDecimal totalDiscount;

    private BigDecimal cityTax;

    private Boolean isPaid;

    @NotNull(message = "supplierId is null")
    private Long supplierId;

    @NotNull(message = "items is null")
    private List<PurchaseItems> purchaseItems;

}

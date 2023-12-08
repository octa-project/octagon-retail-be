package octagon.retail.model.sale;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleModel {

    @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty;

    @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount;

    private LocalDateTime date;
    private BigDecimal paidTotalAmount;
    private Boolean isPaid;
    private Boolean isDeleted;
    private Long branchId;
    private List<SaleItemModel> saleItems;
}
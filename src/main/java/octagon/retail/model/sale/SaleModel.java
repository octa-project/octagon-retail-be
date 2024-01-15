package octagon.retail.model.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SaleModel {

    @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty;

    @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount;

    private Date date;
    private BigDecimal paidTotalAmount;
    private Boolean isPaid;
    private Boolean isDeleted;
    private Long branchId;
    private Long createdUserId;

}
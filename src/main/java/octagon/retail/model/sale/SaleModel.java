package octagon.retail.model.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import octagon.retail.entity.SaleItems;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleModel {

    private Long id;
    // @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty;

    // @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount;

    private Date date;
    private BigDecimal paidTotalAmount;
    private Boolean isPaid;
    private Boolean isDeleted;
    private Long branchId;
    private Long createdUserId;
    private List<SaleItems> stock;

}
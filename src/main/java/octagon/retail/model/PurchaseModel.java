package octagon.retail.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import octagon.retail.entity.PurchaseItems;

@Data
@AllArgsConstructor
public class PurchaseModel {
    public PurchaseModel() {
    }

    private Long id;

    @NotNull(message = "date is null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

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

package octagon.retail.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import octagon.retail.entity.Items;
import octagon.retail.entity.Purchase;
import octagon.retail.entity.PurchaseItems;
import octagon.retail.model.item.ItemCodeModel;

@Data
public class PurchaseModel {
    private Long id;

    @NotNull(message = "date is null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private BigDecimal totalAmount;

    private BigDecimal totalQty;

    private BigDecimal totalCost;

    private BigDecimal vat;

    private BigDecimal cityTax;

    private Boolean isPaid;

    @NotNull(message = "supplierId is null")
    private Long supplierId;

    @NotNull(message = "items is null")
    private List<PurchaseItems> purchaseItems;

}

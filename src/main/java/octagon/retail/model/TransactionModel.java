package octagon.retail.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class TransactionModel {
    private List<TransactionInvoiceModel> data;

    private Double totalAmount;
    private Double totalCashAmount;
    private Double totalNonCashAmount;

    private Double totalCardAmount;
    private Double totalAccountAmount;
}

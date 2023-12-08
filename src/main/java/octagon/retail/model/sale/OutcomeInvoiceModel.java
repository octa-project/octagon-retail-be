package octagon.retail.model.sale;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OutcomeInvoiceModel {
    private String barcode;
    private String name;
    private BigDecimal qty;

}

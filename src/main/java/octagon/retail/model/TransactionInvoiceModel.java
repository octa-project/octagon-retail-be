package octagon.retail.model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;
import octagon.retail.repository.payment.TransactionType;

@Data
public class TransactionInvoiceModel {
    private Date date;
    private String name;
    private Double amount;
    private TransactionType transactionType;
}

package octagon.retail.model.bank;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionModel {

    private Long id;
    private Long saleId;
    private String name;
    private BigDecimal amount;
    private Integer transactionType;
    private BigDecimal bankId;
    private Long bankTransactionId;
    private Boolean isDeleted;
    private Long branchId;

    public TransactionModel(Long id, Long saleId, String name, BigDecimal amount, Integer transactionType, BigDecimal bankId, Long bankTransactionId, Boolean isDeleted, Long branchId) {
        this.id = id;
        this.saleId = saleId;
        this.name = name;
        this.amount = amount;
        this.transactionType = transactionType;
        this.bankId = bankId;
        this.bankTransactionId = bankTransactionId;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}


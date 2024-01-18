package octagon.retail.model.bank;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BankTransactionModel {

    private Long id;
    private Long saleId;
    private String name;
    private String pan;
    private String operationCode;
    private String entryMode;
    private Date date;
    private String rrn;
    private String bankId;
    private String dbRefNo;
    private String terminalId;
    private String approvalCode;
    private BigDecimal amount;
    private String traceNo;
    private String data;
    private Boolean isDeleted;
    private Long branchId;

    public BankTransactionModel(Long id, Long saleId, String name, String pan, String operationCode, String entryMode,
            Date date, String rrn, String bankId, String dbRefNo, String terminalId, String approvalCode,
            BigDecimal amount, String traceNo, String data, Boolean isDeleted, Long branchId) {
        this.id = id;
        this.saleId = saleId;
        this.name = name;
        this.pan = pan;
        this.operationCode = operationCode;
        this.entryMode = entryMode;
        this.date = date;
        this.rrn = rrn;
        this.bankId = bankId;
        this.dbRefNo = dbRefNo;
        this.terminalId = terminalId;
        this.approvalCode = approvalCode;
        this.amount = amount;
        this.traceNo = traceNo;
        this.data = data;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

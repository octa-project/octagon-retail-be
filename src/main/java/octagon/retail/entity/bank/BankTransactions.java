package octagon.retail.entity.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import octagon.retail.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_transactions")
public class BankTransactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sale_id")
    private Long saleId;

    @Column(name = "name")
    private String name;

    @Column(name = "pan")
    private String pan;

    @Column(name = "operation_code")
    private String operationCode;

    @Column(name = "entry_mode")
    private String entryMode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private Date date;

    @Column(name = "rrn")
    private String rrn;

    @Column(name = "bank_id")
    private String bankId;

    @Column(name = "db_ref_no")
    private String dbRefNo;

    @Column(name = "terminal_id")
    private String terminalId;

    @Column(name = "approval_code")
    private String approvalCode;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "trace_no")
    private String traceNo;

    @Column(name = "data")
    private String data;

    @Column(name = "is_deleted")
    private Boolean  isDeleted;

    @Column(name = "branch_id")
    private Long branchId;

}
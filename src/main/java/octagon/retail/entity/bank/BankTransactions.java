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
    private Long id;

    private Long saleId;

    private String name;

    private String pan;

    private String operationCode;

    private String entryMode;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String rrn;

    private String bankId;

    private String dbRefNo;

    private String terminalId;

    private String approvalCode;

    private BigDecimal amount;

    private String traceNo;

    private String data;

    private Boolean  isDeleted = true;

    private Long branchId;

}
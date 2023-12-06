package octagon.retail.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import octagon.retail.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
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
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    @NotNull
    private Long saleId;
    @NotNull
    private String name;
    @NotNull
    private String pan;
    @NotNull
    private String operationCode;
    @NotNull
    private String entryMode;
    @NotNull
    private String rrn;
    @NotNull
    private String bankId;
    @NotNull
    private String dbRefNo;
    @NotNull
    private String terminalId;
    private String approvalCode;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String traceNo;
    private String data;
    private Boolean  isDeleted = false;
    private Long branchId;
}
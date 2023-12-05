package octagon.retail.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @NotNull
    private Long saleId;

    @NotNull
    private String transactionName;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Integer transactionTypeId;

    private BigDecimal bankId;

    private Long bankTransactionId;

    private Boolean isDeleted = false;

    private Long branchId;
}
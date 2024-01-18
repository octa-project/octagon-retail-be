package octagon.retail.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import octagon.retail.utils.TransactionType;
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
    private Long saleId;
    private String transactionName;
    private BigDecimal amount;
    private TransactionType type;
    private Long bankTransactionId;
}
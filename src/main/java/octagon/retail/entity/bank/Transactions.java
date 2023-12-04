package octagon.retail.entity.bank;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import octagon.retail.entity.BaseEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long saleId;

    private String name;

    private BigDecimal amount;

    private Integer transactionType;

    private BigDecimal bankId;

    private Long bankTransactionId;

    private Boolean isDeleted = true;

    private Long branchId;
}
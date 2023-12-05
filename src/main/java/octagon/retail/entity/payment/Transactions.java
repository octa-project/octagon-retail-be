package octagon.retail.entity.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;

import java.math.BigDecimal;

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

    @NotNull
    private Long saleId;

    @NotNull
    private String transactionName;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private int transactionTypeId;

    private BigDecimal bankId;

    private Long bankTransactionId;

    private Boolean isDeleted = false;

    private Long branchId;
}
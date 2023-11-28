package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long saleId;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "bank_id")
    private BigDecimal bankId;

    @Column(name = "bank_transaction_id")
    private Long bankTransactionId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "branch_id")
    private Long branchId;
}
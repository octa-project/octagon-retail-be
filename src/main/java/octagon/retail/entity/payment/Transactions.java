package octagon.retail.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.sale.Sales;
import octagon.retail.repository.payment.TransactionType;

import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saleId")
    private Sales sale;

    @OneToOne
    @JoinColumn(name = "bank_transaction_id")
    private BankTransactions bankTransactionId;
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    @NotNull
    private String transactionName;

    @NotNull
    private Double amount;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    private Boolean isDeleted = false;

    private Long branchId;
}

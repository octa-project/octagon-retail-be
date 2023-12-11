package octagon.retail.entity.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import octagon.retail.entity.payment.Transactions;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sales extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    // @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty = BigDecimal.ZERO;

    // @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    private BigDecimal paidTotalAmount = BigDecimal.ZERO;

    private Boolean isPaid = false;

    private Boolean isDeleted = false;

    private Long branchId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "saleId", referencedColumnName = "id", insertable = false, updatable = false)
    private List<SaleItems> stocks;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "saleId", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Transactions> transactions;
    //
    // @OneToMany(fetch = FetchType.LAZY)
    // @Fetch(FetchMode.JOIN)
    // @JoinColumn(name = "transaction_id", referencedColumnName = "id", insertable
    // = false, updatable = false)
    // private List<Transactions> transactions;

}
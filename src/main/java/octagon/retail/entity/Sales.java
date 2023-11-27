package octagon.retail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sales extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private Date date;

    @Column(name = "total_qty")
    private BigDecimal totalQty;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "paid_total_amount")
    private BigDecimal paidTotalAmount;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "sale_id", referencedColumnName = "id",  insertable = false, updatable = false)
    private List<SaleItems> saleItems;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id",  insertable = false, updatable = false)
    private List<Transactions> transactions;

}

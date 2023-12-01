package octagon.retail.entity.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
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
    @Column
    private Long id;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private Date date;

    @Column
    @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty;

    @Column
    @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount;

    @Column
    private BigDecimal paidTotalAmount;

    @Column
    private Boolean isPaid = false;

    @Column
    private Boolean isDeleted = false;

    @Column
    private Long branchId;

    @Column
    private Long createdUserId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "saleId", referencedColumnName = "id",  insertable = false, updatable = false)
    private List<SaleItems> saleItems;
    //
    //    @OneToMany(fetch = FetchType.LAZY)
    //    @Fetch(FetchMode.JOIN)
    //    @JoinColumn(name = "transaction_id", referencedColumnName = "id",  insertable = false, updatable = false)
    //    private List<Transactions> transactions;

}
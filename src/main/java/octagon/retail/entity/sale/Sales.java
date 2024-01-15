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
import java.time.LocalDateTime;
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


    private BigDecimal totalQty;


    private String user;


    private BigDecimal totalAmount;


    private BigDecimal paidTotalAmount;

    private Boolean isPaid = false;

    private Boolean isDeleted = false;

    private Long branchId;

    private Long createdUserId;

    //
    //    @OneToMany(fetch = FetchType.LAZY)
    //    @Fetch(FetchMode.JOIN)
    //    @JoinColumn(name = "transaction_id", referencedColumnName = "id",  insertable = false, updatable = false)
    //    private List<Transactions> transactions;

}
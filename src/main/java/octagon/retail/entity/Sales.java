package octagon.retail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.utils.SaleType;

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

    // @NotNull(message = "Total quality should not be null")
    private BigDecimal totalQty;

    // @NotNull(message = "Total amount not be null")
    private BigDecimal totalAmount;

    private BigDecimal paidTotalAmount;

    private Boolean isPaid = false;

    private Boolean isDeleted = false;

    private Long branchId;

    private SaleType type = SaleType.INIT;

}

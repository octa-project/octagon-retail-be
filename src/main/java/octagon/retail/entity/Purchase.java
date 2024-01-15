package octagon.retail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase")
public class Purchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private BigDecimal totalAmount;

    private BigDecimal totalQty;

    private BigDecimal totalCost;

    private BigDecimal vat;

    private BigDecimal cityTax;

    private BigDecimal totalDiscount;

    private Boolean isPaid;

    public Long supplierId;

    public Long branchId;

    public String description;

    // @Column(columnDefinition = "jsonb")
    // @Type(JsonType.class)
    // private List<Long> purchaseItems;

}

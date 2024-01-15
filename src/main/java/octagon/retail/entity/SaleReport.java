package octagon.retail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_report")
public class SaleReport extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name")
    private String name;

    @Column(name = "itemGroup")
    private String itemGroup;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "itemPrice")
    private Integer itemPrice;

    @Column(name = "profit")
    private BigDecimal profit;

    @Column(name = "itemDiscount")
    private BigDecimal itemDiscount;

    @Column(name = "cardDiscount")
    private BigDecimal cardDiscount;

    @Column(name = "totalAmount")
    private Integer totalAmount;

    @Column(name = "vat")
    private Integer vat;

    @Column(name = "cityTax")
    private Integer cityTax;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "total")
    private Integer total;

    @Column(name = "user")
    private String user;

    @Column(name = "employee")
    private String employee;

    @Column(name = "branchId")
    private Long branchId;

}

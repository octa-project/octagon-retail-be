package octagon.retail.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_items")
public class PurchaseItems extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;

    private String itemName;

    private BigDecimal sellPrice;

    private BigDecimal costPrice;

    private Long purchaseId;

    private BigDecimal discount;

    private BigDecimal qty;

    // private
}

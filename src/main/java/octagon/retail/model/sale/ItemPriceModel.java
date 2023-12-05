package octagon.retail.model.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemPriceModel {

    private Long id;
    private Long itemId;
    private Long itemCodeId;
    private BigDecimal salePrice;
    private Date date;
    private Boolean isDeleted;
    private Long branchId;

    public ItemPriceModel(Long id, Long itemId, Long itemCodeId, BigDecimal salePrice, Date date, Boolean isDeleted, Long branchId) {
        this.id = id;
        this.itemId = itemId;
        this.itemCodeId = itemCodeId;
        this.salePrice = salePrice;
        this.date = date;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

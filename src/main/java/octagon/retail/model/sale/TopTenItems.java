package octagon.retail.model.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TopTenItems {
    private BigDecimal qty;
    private String itemBarCode;
    private String itemName;

}

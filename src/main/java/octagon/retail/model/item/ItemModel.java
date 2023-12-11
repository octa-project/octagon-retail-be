package octagon.retail.model.item;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemModel {
    private Long id;
    private String code;
    private String name;
    private Long measureId;
    private String measureName;
    private Long itemgroupId;
    private String itemgroupName;
    private Boolean isActive;
    private Boolean isDeleted;
    private Long branchId;
    private LocalDateTime createdDate;
    private List<ItemCodeModel> itemcodes;
}

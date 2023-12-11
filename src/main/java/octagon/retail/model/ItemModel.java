package octagon.retail.model;

import lombok.Data;
import octagon.retail.service.ItemCodeService;

import java.time.LocalDateTime;
import java.util.Date;
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

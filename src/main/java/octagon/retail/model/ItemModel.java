package octagon.retail.model;

import lombok.Data;
import octagon.retail.service.ItemCodeService;

import java.util.List;

@Data
public class ItemModel {
    private Long id;
    private String code;
    private String name;
    private  Long measureId;
    private String measureName;
    private Boolean isActive;
    private Boolean isDeleted;
    private Long branchId;

    private List<ItemCodeModel> itemcodes;
 }

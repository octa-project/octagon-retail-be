package octagon.retail.model;

import lombok.Data;

@Data
public class ItemModel {
    private Long id;
    private String code;
    private String name;
    private Long measureId;
    private Long itemGroupId;
    private Boolean isActive;
    private Boolean isDeleted;
    private Long branchId;

    public ItemModel(
            Long id,
            String code,
            String name,
            Long measureId,
            Long itemGroupId,
            Boolean isActive,
            Boolean isDeleted,
            Long branchId
    ){
        this.id = id;
        this.code = code;
        this.name = name;
        this.measureId = measureId;
        this.itemGroupId = itemGroupId;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

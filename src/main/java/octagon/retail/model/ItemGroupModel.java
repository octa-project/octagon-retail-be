package octagon.retail.model;

import lombok.Data;

@Data
public class ItemGroupModel {
    private Long id;
    private String name;
    private String code;
    private Long parentId;
    private String color;
    private Boolean isDeleted;
    private Long branchId;

    public ItemGroupModel(
            Long id,
            String name,
            String code,
            Long parentId,
            String color,
            Boolean isDeleted,
            Long branchId
            ){
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.color = color;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

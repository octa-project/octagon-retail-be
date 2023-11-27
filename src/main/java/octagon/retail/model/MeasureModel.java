package octagon.retail.model;

import lombok.Data;

@Data
public class MeasureModel {
    private Long id;
    private String name;
    private String code;
    private Boolean isDeleted;
    private Long branchId;

    public MeasureModel(
            Long id,
            String name,
            String code,
            Boolean isDeleted,
            Long branchId
    ){
        this.id = id;
        this.name = name;
        this.code = code;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}

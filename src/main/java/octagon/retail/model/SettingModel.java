package octagon.retail.model;

import lombok.Data;

@Data
public class SettingModel {
    private Long id;
    private String name;
    private String taxNumber;
    private String address;
    private Long branchId;
    private boolean isDeleted;

    public SettingModel(
            Long id,
            String name,
            String taxNumber,
            String address,
            Long branchId,
            boolean isDeleted
    ) {
        this.id = id;
        this.name = name;
        this.taxNumber = taxNumber;
        this.address = address;
        this.branchId = branchId;
        this.isDeleted = isDeleted;
    }
}

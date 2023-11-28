package octagon.retail.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BankModel {

    private Long id;
    private String code;
    private String name;
    private Boolean isActive;
    private Integer bankType;
    private Boolean isDeleted;
    private Long branchId;

    public BankModel(Long id, String code, String name, Boolean isActive, Integer bankType, Boolean isDeleted, Long branchId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.bankType = bankType;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
    }
}


package octagon.retail.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SettingModel {

    private Long id;
    private String taxNumber;
    private String name;
    private Long createdUserId;
    private boolean isActive;
    private boolean isDeleted;
    private Long branchId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public SettingModel(Long id, String taxNumber, String name, Long createdUserId, boolean isActive, boolean isDeleted,
                        Long branchId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.taxNumber = taxNumber;
        this.name = name;
        this.createdUserId = createdUserId;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.branchId = branchId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}

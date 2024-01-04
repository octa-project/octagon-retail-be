package octagon.retail.model.item;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemSaveModel {
    private Long id;
    private String code;
    private String name;
    private Long measureId;
    private Long itemgroupId;
    private Boolean isActive;
    private Boolean isDeleted;
    private Long branchId;
    private LocalDateTime createdDate;
}

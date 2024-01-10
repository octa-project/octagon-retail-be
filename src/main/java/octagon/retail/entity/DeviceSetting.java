package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device_settings")
public class DeviceSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String portName;
    private Integer portType;
    private Integer paperType;
    private String FooterText;
    private String ipAddress;
    private Integer leftMargin;
    private boolean isActive;
    private boolean isDeleted;
    private Long branchId;

}

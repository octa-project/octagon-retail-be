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
public class DeviceSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "retail_device_name")
    private String retailDeviceName;

    @Column(name ="name")
    private String name;

    @Column(name ="port_name")
    private String portName;

    @Column(name ="port_type")
    private Integer portType;

    @Column(name ="paper_type")
    private Integer paperType;

    @Column(name ="footer_text")
    private String FooterText;

    @Column(name ="ip_address")
    private String ipAddress;

    @Column(name ="left_margin")
    private Integer leftMargin;

    @Column(name ="is_active")
    private boolean isActive;

    @Column(name ="is_deleted")
    private boolean isDeleted;

    @Column(name ="branch_id")
    private Long branchId;

    @Column(name ="created_date")
    private LocalDateTime createdDate;

    @Column(name ="modified_date")
    private LocalDateTime ModifiedDate;

}

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
public class PaymentSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name ="code")
    private String code;

    @Column(name ="name")
    private String name;

    @Column(name ="settings_id")
    private String settings_id;

    @Column(name ="password")
    private String password;

    @Column(name ="created_user_id")
    private Long createdUserId;

    @Column(name ="is_active")
    private Boolean isActive;

    @Column(name ="is_deleted")
    private Boolean isDeleted;

    @Column(name ="branch_id")
    private Long branchId;

    @Column(name ="created_date")
    private LocalDateTime createdDate;

    @Column(name ="modified_date")
    private LocalDateTime modifiedDate;

}

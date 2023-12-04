package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "settings")
public class Settings extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "created_user_id")
    private Long createdUserId;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "created_date")
    private LocalDateTime createdDate ;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate ;

}

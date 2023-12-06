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
    private Long id;

    private String taxNumber;

    private String name;

    private String address;

    private Long createdUserId;

    private Boolean isActive;

    private Boolean isDeleted;

    private Long branchId;

    private LocalDateTime createdDate ;

    private LocalDateTime modifiedDate ;

}

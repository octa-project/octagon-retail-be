package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String city;

    private Boolean isActive;

    private Long branchId;

    private String contractNumber;

    private String phone;

    private String motto;

}

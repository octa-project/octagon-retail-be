package octagon.retail.entity.bank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banks")
public class Banks extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private Boolean isActive = false;

    private Integer bankType;

    private Boolean isDeleted = false;

    private Long branchId;

}

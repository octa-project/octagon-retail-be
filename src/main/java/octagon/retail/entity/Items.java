package octagon.retail.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Items extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "code")
    private String code;
    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "itemgroup_id")
    private Long itemgroupId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "branch_id")
    private Long branchId;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ItemCodes> itemCodes;

}

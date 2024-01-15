package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "item_group", indexes = {
        @Index(name = "idx_parent_id", columnList = "parent_id") })
public class ItemGroups extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Double code;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "color")
    private Long color;

    @Column(name = "is_primary")
    private Boolean isPrimary;
}
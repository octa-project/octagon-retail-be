package octagon.retail.entity.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import octagon.retail.entity.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_types")
public class TransactionType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String icon;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TransactionType parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<TransactionType> children = new ArrayList<>();

    private Boolean isActive = true;

    private Boolean isDeleted = false;

}

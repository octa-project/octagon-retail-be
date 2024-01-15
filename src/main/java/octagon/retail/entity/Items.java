package octagon.retail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items", indexes = {
        @Index(name = "idx_code", columnList = "code") })
public class Items extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "code")
    private String code;

    @Column(name = "vat_code")
    private String vatCode;

    @Column(name = "name")
    private String name;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "measure_id")
    private Long measureId;

    @Column(name = "business_cat_id")
    private Long businessCatId;

    @Column(name = "item_group_id")
    private Long itemGroupId;

    @Column(name = "is_vat")
    private Boolean isVat;

    @Column(name = "is_city_tax")
    private Boolean isCityTax;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_complete")
    private Boolean isComplete = false;

    @Column(name = "image_url")
    private String imageUrl;

}

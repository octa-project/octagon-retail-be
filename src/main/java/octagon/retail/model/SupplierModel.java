package octagon.retail.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierModel {

    private Long id;

    @NotNull(message = "code is null")
    private String code;

    @NotNull(message = "name is null")
    private String name;

    @NotNull(message = "email is null")
    private String email;

    @NotNull(message = "phone is null")
    private String phone;

    @NotNull(message = "taxNumber is null")
    private String taxNumber;
}

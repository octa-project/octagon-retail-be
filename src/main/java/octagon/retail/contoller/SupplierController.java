package octagon.retail.contoller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import octagon.retail.entity.Supplier;
import octagon.retail.model.SupplierModel;
import octagon.retail.reponse.ResponseModel;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    octagon.retail.service.SupplierService service;

    @PostMapping("/add")
    public ResponseEntity<ResponseModel<Supplier>> addSupplier(@RequestBody @Valid SupplierModel body) {
        return service.addSupplier(body);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseModel<List<Supplier>>> getAll() {
        return service.getSuppliers();
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModel<Supplier>> updateSupplier(
            @RequestBody @Valid SupplierModel body) {
        return service.updateSupplier(body);
    }
}

package octagon.retail.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import octagon.retail.entity.Sales;
import octagon.retail.entity.Supplier;
import octagon.retail.model.SupplierModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.repository.ICustomerSuppliersRepository;

@Log4j2
@Service
public class SupplierService {
    @Autowired
    ICustomerSuppliersRepository suppliersRepository;

    public ResponseEntity<ResponseModel<Supplier>> addSupplier(SupplierModel request) {
        try {
            var sup = suppliersRepository.findByCode(request.getCode()).orElse(null);
            if (sup != null)
                return ResponseEntity.ok(new ResponseModel<>("500", "Code Exist" + sup.getCode(), false,
                        null));

            sup = new Supplier();
            sup.setCode(request.getCode());
            sup.setEmail(request.getEmail());
            sup.setName(request.getName());
            sup.setPhone(request.getPhone());
            sup.setTaxNumber(request.getTaxNumber());
            var saved = suppliersRepository.save(sup);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    saved));
        } catch (Exception e) {
            log.info("addSupplier:{}", e.getMessage());
            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }

    public ResponseEntity<ResponseModel<Supplier>> updateSupplier(SupplierModel request) {
        try {
            var sup = suppliersRepository.findById(request.getId()).orElse(null);
            if (sup == null)
                return ResponseEntity.ok(new ResponseModel<>("500", "No Supplier", false,
                        null));

            sup.setCode(request.getCode());
            sup.setEmail(request.getEmail());
            sup.setName(request.getName());
            sup.setPhone(request.getPhone());
            sup.setTaxNumber(request.getTaxNumber());
            var saved = suppliersRepository.save(sup);
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    saved));
        } catch (Exception e) {
            log.info("addSupplier:{}", e.getMessage());
            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }

    public ResponseEntity<ResponseModel<List<Supplier>>> getSuppliers() {
        try {
            var sups = suppliersRepository.findAll();
            if (sups == null)
                return ResponseEntity.ok(new ResponseModel<>("500", "No Supplier", false,
                        null));

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true,
                    sups));
        } catch (Exception e) {
            log.info("addSupplier:{}", e.getMessage());
            return ResponseEntity.ok(new ResponseModel<>("500", e.getMessage(), false,
                    null));
        }
    }
}

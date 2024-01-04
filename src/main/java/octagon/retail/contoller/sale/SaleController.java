package octagon.retail.contoller.sale;

import jakarta.validation.Valid;
import octagon.retail.entity.sale.Sales;
import octagon.retail.model.sale.OutcomeInvoiceModel;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("save")
    public ResponseEntity<ResponseModel<Sales>> save(@Valid @RequestBody Sales sale) {
        return saleService.saveSale(sale);
    }

    @PostMapping("instatiate")
    public ResponseEntity<ResponseModel<Sales>> instatiaSale() {
        return saleService.instatiateSale();
    }

    @PutMapping("update")
    public ResponseEntity<ResponseModel<Sales>> update(@RequestParam("id") Long id, @Valid @RequestBody Sales sale) {
        return saleService.updateSale(id, sale);
    }

    @PostMapping("isPaid")
    public ResponseEntity<ResponseModel<Sales>> isPaid(@RequestParam("id") Long id, @RequestBody Sales sale) {
        return saleService.isPaid(id, sale);
    }

    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<Sales>>> getMany(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return saleService.getMany(startDate, endDate);
    }

    @GetMapping("send-outcome-by-date")
    public ResponseEntity<ResponseModel<List<OutcomeInvoiceModel>>> sendOutComeByData(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return saleService.sendOutcomeByData(startDate, endDate);
    }

    @GetMapping("get-one")
    public ResponseEntity<ResponseModel<Sales>> getOne(Long id) {
        return saleService.getOne(id);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseModel<Sales>> getSaleItemById(Long id) {
        return saleService.deleteSale(id);
    }

    @GetMapping("get-dashboard-data")
    public ResponseEntity<ResponseModel<Object>> getDashboardData(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return saleService.getDashboardData(date);
    }

    @GetMapping("get-all-sales")
    public ResponseEntity<ResponseModel<Object>> getAllSales() {
        return saleService.getAllSales();
    }
}
package octagon.retail.contoller.sale;

import jakarta.validation.Valid;
import octagon.retail.entity.sale.Sales;
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

    @PutMapping("update")
    public ResponseEntity<ResponseModel<Sales>> update(@Valid @RequestBody Sales sale, Long id) {
        return saleService.updateSale(id,sale);
    }

    @PutMapping("updateQtyAmount")
    public ResponseEntity<ResponseModel<Sales>> updateQtyAmount(@RequestBody Sales sale, Long id) {
        return saleService.updateQtyAmountSale(id,sale);
    }

    @PutMapping("isPaid")
    public ResponseEntity<ResponseModel<Sales>> isPaid(@RequestBody Sales sale, Long id) {
        return saleService.isPaid(id,sale);
    }

    @GetMapping("get-many")
    public ResponseEntity<ResponseModel<List<Sales>>> getMany(String startDate, String endDate) {
        return saleService.getMany(startDate,endDate);
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
    public ResponseEntity<ResponseModel<Object>> getDashboardData(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return saleService.getDashboardData(date);
    }

    @GetMapping("get-daily-income")
    public ResponseEntity<ResponseModel<Object>> getDailyIncome(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return saleService.getDailyIncome(date);
    }

    @GetMapping("get-all-sales")
    public ResponseEntity<ResponseModel<Object>> getAllSales() {
        return saleService.getAllSales();
    }
}
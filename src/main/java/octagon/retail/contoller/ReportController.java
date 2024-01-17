package octagon.retail.contoller;

import net.sf.jasperreports.engine.JRException;
import octagon.retail.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/sale-report")
    public String getSaleReport(@RequestBody Map<String,Object> parameter) throws JRException, FileNotFoundException, ParseException {
        return reportService.getSaleReport(parameter);
    }

    @PostMapping("/report-test")
    public String getSaleReport() throws JRException, FileNotFoundException {
        return reportService.reportTest();
    }



}

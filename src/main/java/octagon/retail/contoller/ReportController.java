package octagon.retail.contoller;

import net.sf.jasperreports.engine.JRException;
import octagon.retail.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Map;

import java.nio.file.Path;

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

    @GetMapping("/getfile")
    public ResponseEntity<Resource> getFileTest() throws MalformedURLException {
        File file = new File("D:\\Jasper" + "\\saleReport.html");
        Path filePath = Paths.get(file.getAbsolutePath());;
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }



}

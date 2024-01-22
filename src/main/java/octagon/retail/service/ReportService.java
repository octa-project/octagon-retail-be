package octagon.retail.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import octagon.retail.model.SaleReportModel;
import octagon.retail.repository.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    public String getSaleReport(Map<String, Object> parameter) throws ParseException, FileNotFoundException, JRException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<SaleReportModel> listSaleItems = saleRepository.getSaleReport();
        File file = ResourceUtils.getFile("classpath:testSaleReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listSaleItems);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", "2024-01-01");
        parameters.put("endDate", "2024-01-19");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\Jasper" + "\\saleReport.html");
        return "Success";
    }
}

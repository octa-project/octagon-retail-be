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
@Service
public class ReportService {
    @Autowired
    private SaleRepository saleRepository;
    public String getSaleReport(Map<String, Object> parameter) throws ParseException, FileNotFoundException, JRException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<SaleReportModel> listSaleItems = saleRepository.getSaleReport();
// for(int i = 0; i<50; i++) {
// SaleReportModel sale3 = new SaleReportModel();
// sale3.setDate(dateFormat.parse("2024-02-01"));
// sale3.setTotalAmount(new BigDecimal(65000 + i));
// sale3.setBarcode(String.valueOf(i));
// sale3.setQty(new BigDecimal(10+i));
// sale3.setItemPrice(new BigDecimal(1000+i));
// sale3.setVat(Integer.valueOf(3333+i));
// sale3.setName("Coca cola");
// sale3.setProfit(new BigDecimal(0));
// listSaleItems.add(sale3);
// }
        File file = ResourceUtils.getFile("classpath:testSaleReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listSaleItems);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", "2024-01-01");
        parameters.put("endDate", "2024-01-17");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\Jasper" + "\\saleReport.pdf");
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\Jasper" + "\\saleReport.html");
        return "Success";
    }
    public String reportTest() throws FileNotFoundException, JRException {
// List<SaleReportModel> listSaleItems = saleRepository.getSaleReport();
        List<SaleReportModel> listSaleItems = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:Cherry.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listSaleItems);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\Jasper" + "\\Cherry.pdf");
        return "";
    }
}
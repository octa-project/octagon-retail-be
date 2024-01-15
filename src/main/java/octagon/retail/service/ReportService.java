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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    public String getSaleReport(Map<String, Object> parameter) throws FileNotFoundException, JRException {
        List<SaleReportModel> listSaleItems = saleRepository.getSaleReport();
        File file = ResourceUtils.getFile("classpath:saleReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listSaleItems);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "D:Jasper" + "\\saleReport.pdf");
        return "";
    }

}

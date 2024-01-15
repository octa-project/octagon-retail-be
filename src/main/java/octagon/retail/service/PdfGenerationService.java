package octagon.retail.service;
import octagon.retail.entity.PdfDocument;
import octagon.retail.repository.IPdfDocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {

    @Autowired
    private IPdfDocumentRepository pdfDocumentRepository;

    public void generateAndSavePdf(String content) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(content);
                contentStream.endText();
            }

            PdfDocument pdfDocument = new PdfDocument();
            pdfDocument.setContent(content);
            pdfDocumentRepository.save(pdfDocument);

            String filePath = "D:\\Jasper\\test.pdf";
            savePdfToFile(document, filePath);
        }
    }

    private void savePdfToFile(PDDocument document, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            document.save(outputStream);
        }
    }
}
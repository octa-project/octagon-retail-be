package octagon.retail.contoller;

import octagon.retail.entity.PdfDocument;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*")
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/generate")
    public ResponseEntity<String> generatePdf() {
        try {
            String content = "";
            pdfGenerationService.generateAndSavePdf(content);
            return ResponseEntity.ok("Амжилттай үүслээ");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("PDF үүсхэд алдаа гарлаа " + e.getMessage());
        }
    }
}
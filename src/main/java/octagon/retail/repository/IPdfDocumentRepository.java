package octagon.retail.repository;

import octagon.retail.entity.PdfDocument;
import org.springframework.stereotype.Repository;

@Repository
public interface IPdfDocumentRepository extends MainRepository<PdfDocument , Long>{
}

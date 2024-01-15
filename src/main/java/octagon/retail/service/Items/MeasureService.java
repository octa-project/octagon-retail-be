package octagon.retail.service.Items;

import octagon.retail.entity.Measures;
import octagon.retail.repository.IMeasureRepository;
import octagon.retail.response.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureService {

    @Autowired
    private IMeasureRepository measureRepository;

    public ResponseEntity<ResponseModel<Measures>> saveMeasure(Measures measure) {
        measureRepository.save(measure);
        return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, measure));
    }

    public ResponseEntity<ResponseModel<Measures>> updateMeasure(Measures measure) {
        Measures existingMeasure = measureRepository.findById(measure.getId()).orElse(null);
        if (existingMeasure != null) {
            existingMeasure.setName(measure.getName());
            existingMeasure.setCode(measure.getCode());
            existingMeasure.setBranchId(measure.getBranchId());
            measureRepository.save(existingMeasure);

            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, existingMeasure));
        }
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Measures>> getMeasureById(Long measureId) {
        Measures measure = measureRepository.findById(measureId).orElse(null);
        if (measure != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, measure));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<List<Measures>>> getMeasures() {
        List<Measures> measures = measureRepository.findAll();
        if (measures != null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, measures));
        return ResponseEntity.ok(new ResponseModel<>("500", "Амжилтгүй", false, null));
    }

    public ResponseEntity<ResponseModel<Measures>> deleteMeasureById(Long measureId) {
        measureRepository.deleteById(measureId);
        Measures measure = measureRepository.findById(measureId).orElse(null);
        if (measure == null)
            return ResponseEntity.ok(new ResponseModel<>("200", "Амжилттай", true, null));
        return ResponseEntity
                .ok(new ResponseModel<>("500", "Амжилтгүй - алдаа гарлаа ахин оролдон уу", false, measure));
    }
}

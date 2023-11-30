package octagon.retail.contoller;

import octagon.retail.entity.ItemCodes;
import octagon.retail.entity.Items;
import octagon.retail.entity.Measures;
import octagon.retail.reponse.ResponseModel;
import octagon.retail.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measure")
public class MeasureController {

    @Autowired
    private MeasureService measureService;

    @PostMapping("/save-measure")
    public ResponseEntity<ResponseModel<Measures>> saveMeasures(@RequestBody Measures measure) {
        return measureService.saveMeasure(measure);
    }

    @PostMapping("/update-measure")
    public ResponseEntity<ResponseModel<Measures>> updateMeasures(@RequestBody Measures measure) {
        return measureService.updateMeasure(measure);
    }

    @GetMapping("get-measure-by-id")
    public ResponseEntity<ResponseModel<Measures>> getMeasureById(@RequestParam("id") Long id) {
        return measureService.getMeasureById(id);
    }

    @GetMapping("get-all-measures")
    public ResponseEntity<ResponseModel<List<Measures>>> getMeasures() {
        return measureService.getMeasures();
    }
    @DeleteMapping("delete-measure-by-id")
    public ResponseEntity<ResponseModel<Measures>> deleteMeasureById(@RequestParam("id") Long id){
        return measureService.deleteMeasureById(id);
    }
}

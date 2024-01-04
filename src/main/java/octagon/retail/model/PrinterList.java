package octagon.retail.model;

import lombok.Data;

import java.util.List;

@Data
public class PrinterList {

    private List<String> name;

    public PrinterList(List<String> name) {
        this.name = name;
    }
}

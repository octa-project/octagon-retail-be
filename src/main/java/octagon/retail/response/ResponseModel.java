package octagon.retail.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseModel<T> {

    private String code;
    private String message;
    private Boolean isSuccess;
    private T data;

}

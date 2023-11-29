package octagon.retail.reponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseModel<T> implements IResponseModel<T> {
    private String code;
    private String message;
    private Boolean isSuccess;
    private T data;

    @Override
    public String getCode() { return code; }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Boolean getIsSuccess() { return isSuccess; }

    @Override
    public T getData() {
        return data;
    }
}

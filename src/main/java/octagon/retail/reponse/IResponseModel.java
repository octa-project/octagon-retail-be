package octagon.retail.reponse;

public interface IResponseModel<T> {
    String getCode();
    Boolean getIsSuccess();
    String getMessage();
    T getData();
}

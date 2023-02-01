package jpacrud.cms.global.error.exception;

import jpacrud.cms.global.error.model.ErrorCode;
import jpacrud.cms.global.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @Value("${spring.profiles.active}")
    private String active;

    @ExceptionHandler(MethodArgumentNotValidException.class)

    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.error("handleMethodArgumentNotValidException", e);

        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest sRequest) throws IOException {
        log.error("handleEntityNotFoundException", e);
        log.info(active);
        log.info(sRequest.getRequestURI());
        // if(active.equals("prod") || active.equals("rent-prod")){
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("application/json");
//            RequestBody body = RequestBody.create(mediaType, "{\n        \"body\": \"서버 에러 알리미\",\n        \"connectColor\":\"#00C473\",\n        \"connectInfo\": [{\"description\":\""+e.getMessage()+"\"}\n        ]\n    }\n    ");
//            Request request = new Request.Builder()
//                    .url("https://웹훅호출주소")
//                    .method("POST", body)
//                    .addHeader("Content-Type", "application/json")
//                    .build();
//            Response responseEX = client.newCall(request).execute();
//        log.info(responseEX.body().string());
//
//
        // if(active.equals("prod") || active.equals("rent-prod")){

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\n        \"body\": \"오류발생! 오류발생!\",\n        \"connectColor\":\"#00C473\",\n        \"connectInfo\": [{\"description\":\"호출 API 주소:  " + sRequest.getMethod()+ " - " + sRequest.getRequestURI() + "\"},\n        {\"description\":\"에러:  " + e + "\"}\n        ]\n    }\n    "

        );
        log.info(body.toString());

        Request request = new Request.Builder()
                .url("https://웹훅 호출주소")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response responseEX = client.newCall(request).execute();
        log.info(responseEX.body().string());      //  }

        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

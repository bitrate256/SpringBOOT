package jpacrud.cms.global.error.model;

public enum ErrorCode {

    // 표준 응답코드
    // 4xx : 서버 측에서 예상할 수 있는 에러. (요청 문제)
    BAD_REQUEST(400, "BAD_REQUEST", "잘못된 요청입니다."),
    UNAUTHORIZED(401, "UNAUTHORIZED", "인증에 실패하여 권한이 없습니다. 인증이 필요한 리소스에 인증 없이 접근했습니다. Digest 인증이 요구됩니다."),
    FORBIDDEN(403, "FORBIDDEN", "인증은 성공했으나 권한이 없습니다. 서버가 요청을 거부했습니다. 클라이언트가 콘텐츠에 접근할 권리/권한을 갖고있지 않습니다."),
    NOT_FOUND(404, "NOT_FOUND", "찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED", "허용되지 않는 방법. 올바른 메소드로 요청하십시오."),
    NOT_ACCEPTABLE(407, "NOT_ACCEPTABLE", "받아들일 수 없음. 요청은 정상이나, 서버에서 받아들일 수 없는 요청 입니다. 웹 방화벽에 의해 차단되었을 수 있습니다."),
    REQUEST_TIMEOUT(408, "REQUEST_TIMEOUT", "요청시간 초과."),
    // 5xx : 예상하지 못한 에러. (서버 문제)
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "내부 서버 오류. 서버에 오류가 발생했습니다."),
    NOT_IMPLEMENTED(501,"NOT_IMPLEMENTED","요청한 기능 미지원. 서버가 요청을 수행하는데 필요한 기능을 지원하지 않습니다. 구현되지 않은 기능 입니다."),
    BAD_GATEWAY(502,"BAD_GATEWAY","게이트웨이 불량. 게이트웨이가 연결된 서버로부터 잘못된 응답을 받았습니다."),
    SERVICE_TEMPORARILY_UNAVAILABLE(503,"SERVICE_TEMPORARILY_UNAVAILABLE","일시적으로 서비스를 이용할 수 없음. 서버가 과부하 되었을 수 있습니다."),
    GATEWAY_TIMEOUT(504, "GATEWAY_TIMEOUT","게이트웨이 시간초과. 게이트웨이가 연결된 서버로부터 응답을 받을 수 없습니다."),

    // 비표준 응답코드
    INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "올바르지 않은 형식입니다."),
    ENTITY_NOT_FOUND(400, "ENTITY_NOT_FOUND", "해당 엔티티를 찾을 수가 없습니다."),
    INVALID_TYPE_VALUE(400, "INVALID_TYPE_VALUE", "타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(403, "HANDLE_ACCESS_DENIED", "권한이 없습니다."),
    HANDLE_INVALID_TOKEN(401, "HANDLE_INVALID_TOKEN", "토큰이 없거나 올바르지 않습니다."),
    NOT_FOUND_USER(509, "NOT_FOUND_USER", "존재하지 않는 회원입니다."),
    NOT_MATCH_PASSWORD(500,"NOT_MATCH_PASSWORD","아이디와 비밀번호를 확인 해 주세요."),
    DUPLICATE_LOGIN_ID(500,"DUPLICATE_LOGIN_ID","이미 존재하는 ID입니다"),
    DUPLICATE_TID(500,"DUPLICATE_TID","이미 존재하는 TID입니다"),
    DUPLICATE_SALES_CODE(500,"DUPLICATE_SALES_CODE","이미 존재하는 영업자 코드입니다."),
    DUPLICATE_BIZNO(500,"DUPLICATE_BIZNO","이미 존재하는 사업자 등록 번호입니다."),
    NOT_ACCESS_USER(500,"NOT_ACCESS_USER","활동 정지된 계정입니다."),
    NOT_MATCH_BILLING_PASSWORD(509,"NOT_MATCH_BILLING_PASSWORD","비밀번호를 확인해 주세요.")
    ;

    private final int status;
    private final String code;
    private final String message;

   ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}

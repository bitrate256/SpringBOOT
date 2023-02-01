package jpacrud.cms.domain.payment.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PgRegTerminalResDto {
    private String resultCd;
    private String resultMsg;
    private String shopName;
    private String ceoName;
    private String bon;
    private String Tel;
    private String address;
}

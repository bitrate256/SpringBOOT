package jpacrud.cms.domain.payment.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pgpay")
public class PgApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(PgApi.class);

    // 컨셉 : 외부에서 전달한 결제내역을 수신해서 저장하고 리스팅
    // 외부에서 지속적으로 데이터를 밀어넣어 줄수 있는 테스트 환경이 필요.

    // 결제내역 수신

    // 결제내역 리스트

}

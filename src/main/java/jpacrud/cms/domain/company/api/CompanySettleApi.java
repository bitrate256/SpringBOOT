package jpacrud.cms.domain.company.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companysettle")
public class CompanySettleApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(CompanySettleApi.class);
}

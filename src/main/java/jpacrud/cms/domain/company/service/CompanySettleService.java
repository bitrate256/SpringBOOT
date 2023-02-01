package jpacrud.cms.domain.company.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanySettleService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(CompanySettleService.class);
}

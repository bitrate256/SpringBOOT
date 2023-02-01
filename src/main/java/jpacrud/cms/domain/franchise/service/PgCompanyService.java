package jpacrud.cms.domain.franchise.service;

import jpacrud.cms.domain.franchise.api.PgCompanyApi;
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
public class PgCompanyService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(PgCompanyApi.class);
}

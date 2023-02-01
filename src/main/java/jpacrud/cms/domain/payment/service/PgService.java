package jpacrud.cms.domain.payment.service;

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
public class PgService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(PgService.class);

// 1. 망 연결 테스트
//  해당 가맹점의 ID의 Authorization의 key가 유효 판단 (해당 가맹점의 key 검증)
//  예외 )
//        테스트용 ID : TESTID ( 네트워크 수신테스트)
//

//(공통)
//    단말기에서 요청이 들어오면 Authorization의 key가 유효한지 테스트


// 결제 승인(카드사)
//    단말기에서 카드 요청이 들어오면 --> Authorization의 key로 가맹점 찾기
//     단말기로 들어온 데이터로 카드사 결제 요청
//    카드사에서 온 데이터을 단말기로 리턴

// 결제 취소 (카드사)
//    결제 취소요청하면 --> 카드사에 취소요청
//    카드사에서 온 데이터 단말기로 리턴

// 단말기 등록 ()

}

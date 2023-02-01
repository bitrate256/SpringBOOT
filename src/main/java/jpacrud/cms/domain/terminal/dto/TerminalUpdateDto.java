package jpacrud.cms.domain.terminal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TerminalUpdateDto {

    private Long id;
//    private Franchise Franchise; // 가맹점 코드 (franchiseCode)
    private Long franchiseCode;
    private String bno; // 사업자번호 (필수값)
    private String authYn; // 단말기 인증 여부는 운영상태로 판단
    private String tid; // 단말기 번호류
    private String terminalCode; // 단말기 코드
    private String terminalType; // 구분 단말기/수기
    private String terminalPgType; // PG (미분류/웰컴/ksnet)
    private String terminalStatus; // 운영상태 (사용/해지)
    private String franchiseName; // 가맹점 명
    private LocalDateTime transactionStartDate; // 개시거래일 (운영상태 사용 등록시 날짜)
    private String ceoName;
    private String managerContact;

    // 공통
    private LocalDateTime createDate;
    private String createId;
    private LocalDateTime modifyDate;
    private String modifyId;
    private String delYn;
}

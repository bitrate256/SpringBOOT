package jpacrud.cms.domain.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FranchiseCreateDto {

    // PK (자동생성)
    private Long id; // 가맹점 코드
    // FK (id값 참조)
//    private Long userSeq; // 사용자 SEQ (식별자)

    private Long pgCompanyId; // PG사 id
    private Long companyCode; // 업체 코드 -> 상위(소속)업체코드

    // 받아와서 insert할 값
    private String franchiseNickname;
    private String ceoId;
    private String settlementCycle;
    private String paymentCancelTodayYn;
    private String settlementPendingYn;
    private String installmentPending;
    private String paymentLimitForeach;
    private String paymentLimitMonth;
    private String paymentEasyYn;
    private String authYn;
    private String paymentEasyLimitPeriod;
    private String mid;

    // 공통
    private LocalDateTime createDate;
    private String createId;
    private LocalDateTime modifyDate;
    private String modifyId;
    private String delYn;

    // user
    private Long userSeq; // 사용자 SEQ (식별자)
    private String userId; // 사용자 ID (계정)
    private String userPw;
    private String userAuth;
    private String agentName;
    private String agentNickname;
    private String companyType;
    private String ceoName;
    private String ceoAddress;
    private String managerName;
    private String managerContact;
    private String managerEmail;
    private String companyName;
    private String companyContact;
    private String companyNum;
    private String corporationNumber;
    private String companyBusiness;
    private String companySector;
    private String companyAddress;
    private String loginAllowCheck;
    private String contractStatus;
    private double commissionRate;
    private String bankName;
    private String accountHolder;
    private String accountNumber;
}

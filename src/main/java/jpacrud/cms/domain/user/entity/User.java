package jpacrud.cms.domain.user.entity;

import jpacrud.cms.domain.company.dto.CompanyCreateDto;
import jpacrud.cms.domain.franchise.dto.FranchiseCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements Serializable {
    //public class User extends BaseTimeEntity implements UserDetails {
    @Comment("Long id값")
    @Id
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Comment("사용자id(사용자가 입력하는 id)")
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Comment("사용자 비밀번호")
    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @Comment("사용자 권한")
    @Column(name = "user_auth", nullable = false)
    private String userAuth;

    @Comment("업체/가맹점 명")
    @Column(name = "agent_name")
    private String agentName;

    @Comment("업체/가맹점 별칭")
    @Column(name = "agent_nickname")
    private String agentNickname;

    @Comment("사업자 구분")
    @Column(name = "company_type")
    private String companyType;

    @Comment("대표자명")
    @Column(name = "ceo_name")
    private String ceoName;

    @Comment("대표자주소")
    @Column(name = "ceo_address")
    private String ceoAddress;

    @Comment("담당자")
    @Column(name = "manager_name")
    private String managerName;

    @Comment("담당자연락처")
    @Column(name = "manager_contact")
    private String managerContact;

    @Comment("담당자 이메일")
    @Column(name = "manager_email")
    private String managerEmail;

    @Comment("회사명")
    @Column(name = "company_name")
    private String companyName;

    @Comment("회사 전화번호")
    @Column(name = "company_contact")
    public String companyContact;

    @Comment("사업자 번호")
    @Column(name = "company_num", nullable = false)
    private String companyNum;

    @Comment("법인번호")
    @Column(name = "corporation_number", nullable = false)
    private String corporationNumber;

    @Comment("업태")
    @Column(name = "company_business", nullable = false)
    private String companyBusiness;

    @Comment("종목")
    @Column(name = "company_sector", nullable = false)
    private String companySector;

    @Comment("사업자주소")
    @Column(name = "company_address", nullable = false)
    private String companyAddress;

    @Comment("로그인허용여부 (1: 허용/ 2: 차단)")
    @Column(name = "login_allow_check", nullable = false)
    private String loginAllowCheck;

    @Comment("계약상태(1: 신청/ 2: 정상/ 3: 해지)")
    @Column(name = "contract_status", nullable = false)
    private String contractStatus;

    @Comment("수수료율(부동소수점 오차 피하기 위해 Decimal 타입 사용)")
    @Column(name = "commission_rate", nullable = false)
    private double commissionRate;

    @Comment("입금은행명(드롭다운)")
    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Comment("예금주")
    @Column(name = "account_holder", nullable = false)
    private String accountHolder;

    @Comment("계좌번호")
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Comment("생성일자")
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Comment("생성한 계정의 id")
    @Column(name = "create_id", nullable = false)
    private String createId;

    @Comment("수정일자")
    @Column(name = "modify_date_time")
    private LocalDateTime modifyDateTime;

    @Comment("최종 수정한 계정의 id")
    @Column(name = "modify_id")
    private String modifyId;

    @Comment("삭제여부")
    @Column(name = "del_yn", nullable = false)
    private String delYn;

    // 가맹점 생성시 user 함께 생성
    public static User createWithFranchise(FranchiseCreateDto dto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return User.builder()
                .userId(dto.getUserId())
                .userPw(dto.getUserPw())
                .userAuth(dto.getUserAuth())
                .agentName(dto.getAgentName())
                .agentNickname(dto.getAgentNickname())
                .companyType(dto.getCompanyType())
                .ceoName(dto.getCeoName())
                .ceoAddress(dto.getCeoAddress())
                .managerName(dto.getManagerName())
                .managerContact(dto.getManagerContact())
                .managerEmail(dto.getManagerEmail())
                .companyName(dto.getCompanyName())
                .companyContact(dto.getCompanyContact())
                .companyNum(dto.getCompanyNum())
                .corporationNumber(dto.getCorporationNumber())
                .companyBusiness(dto.getCompanyBusiness())
                .companySector(dto.getCompanySector())
                .companyAddress(dto.getCompanyAddress())
                .loginAllowCheck("1")
                .contractStatus(dto.getContractStatus())
                .commissionRate(dto.getCommissionRate())
                .bankName(dto.getBankName())
                .accountHolder(dto.getAccountHolder())
                .accountNumber(dto.getAccountNumber())
                .createDateTime(localDateTime)
                .createId(dto.getCreateId())
                .delYn("Y")
                .build();
    }

    // Company 생성시 user 함께 생성
    public static User createWithCompany(CompanyCreateDto dto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return User.builder()
                .userId(dto.getUserId())
                .userPw(dto.getUserPw())
                .userAuth(dto.getUserAuth())
                .agentName(dto.getAgentName())
                .agentNickname(dto.getAgentNickname())
                .companyType(dto.getCompanyType())
                .ceoName(dto.getCeoName())
                .ceoAddress(dto.getCeoAddress())
                .managerName(dto.getManagerName())
                .managerContact(dto.getManagerContact())
                .managerEmail(dto.getManagerEmail())
                .companyName(dto.getCompanyName())
                .companyContact(dto.getCompanyContact())
                .companyNum(dto.getCompanyNum())
                .corporationNumber(dto.getCorporationNumber())
                .companyBusiness(dto.getCompanyBusiness())
                .companySector(dto.getCompanySector())
                .companyAddress(dto.getCompanyAddress())
                .loginAllowCheck("1")
                .contractStatus(dto.getContractStatus())
                .commissionRate(dto.getCommissionRate())
                .bankName(dto.getBankName())
                .accountHolder(dto.getAccountHolder())
                .accountNumber(dto.getAccountNumber())
                .createDateTime(localDateTime)
                .createId(dto.getCreateId())
                .delYn("Y")
                .build();
    }

    public void delete() {
        this.delYn = "N";
    }
}

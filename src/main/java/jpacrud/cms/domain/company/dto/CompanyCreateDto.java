package jpacrud.cms.domain.company.dto;

import jpacrud.cms.domain.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyCreateDto {

    private Long id;

    // 받아와서 insert할 값
    private int companyLevel;
    private Company parent;
    private String highCompanyPath;

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

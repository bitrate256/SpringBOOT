package jpacrud.cms.domain.user.dto;

import jpacrud.cms.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto extends User {

    private Long id;

    private String userId;
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

    private String createId;

    // ID 중복 여부 (Y : 중복 / N : 중복아님)
    // 프론트와 협의 필요. 프론트 페이지 히든값으로 갖고있는 것이 좋을 것.
    private String duplicateId;
}

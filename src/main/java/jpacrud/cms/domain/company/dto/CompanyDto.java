package jpacrud.cms.domain.company.dto;

import jpacrud.cms.domain.company.entity.Company;
import jpacrud.cms.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDto implements Serializable {

    private Long id;
    private User user;
    private int companyLevel;
    private Company parent;
//    private String highCompanyPath;
    private LocalDateTime createDateTime;
    private String createId;
    private LocalDateTime modifyDateTime;
    private String modifyId;
    private String delYn;

    private List<CompanyDto> children = new ArrayList<>();

    public CompanyDto(Company company) {
    }

    // 미사용
    public void LowerResult(final Company company) {
        this.id = company.getId();
        this.user = company.getUser();
        this.companyLevel = company.getCompanyLevel();
        this.createDateTime = company.getCreateDateTime();
        this.createId = company.getCreateId();
        this.modifyDateTime = company.getModifyDateTime();
        this.modifyId = company.getModifyId();
        this.delYn = company.getDelYn();
        this.parent = company.getParent();
        // 하위조직들 조회가 필요할 때 사용
//        this.children = company.getChildren().stream().map(CompanyDto::new).collect(Collectors.toList());
    }

    // 테스트중. 현재 미사용
//    public CompanyDto(Long id, User user, int companyLevel, Company parent, LocalDateTime createDateTime, String createId, LocalDateTime modifyDateTime, String modifyId, String delYn) {
//        this.id = id;
//        this.user = user;
//        this.companyLevel = companyLevel;
//        this.parent = parent;
//        this.createDateTime = createDateTime;
//        this.createId = createId;
//        this.modifyDateTime = modifyDateTime;
//        this.modifyId = modifyId;
//        this.delYn = delYn;
//    }
//
//
//    public static CompanyDto convertCompanyToDto(Company company) {
//        return Objects.equals(company.getDelYn(), "Y") ?
//                new CompanyDto(company.getId(), company.getUser(), company.getCompanyLevel(), company.getParent(), company.getCreateDateTime(), company.getCreateId(), company.getModifyDateTime(), company.getModifyId(), company.getDelYn()) :
//                new CompanyDto(company.getId(), null, company.getCompanyLevel(), null, null, null, null, null, null);
//
//    }
}

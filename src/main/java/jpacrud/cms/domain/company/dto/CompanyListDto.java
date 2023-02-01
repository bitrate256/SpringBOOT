package jpacrud.cms.domain.company.dto;

import jpacrud.cms.domain.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyListDto {

    private Long id;
    private String companyUserId;
    private int companyLevel;
//    private Long highCompanyCode;
    private String highCompanyPath;

    public CompanyListDto(Company company) {
        this.id = company.getId();
        this.companyLevel = company.getCompanyLevel();
//        this.highCompanyCode = company.getHighCompanyCode();
        this.highCompanyPath = company.getHighCompanyPath();
    }
}

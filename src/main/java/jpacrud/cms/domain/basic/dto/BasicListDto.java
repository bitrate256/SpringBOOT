package jpacrud.cms.domain.basic.dto;

import jpacrud.cms.domain.basic.entity.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicListDto {

    private Long companyId;
    private int companyNum;
    private String companyName;
    private String companyContent;

    private String useYN;

    public BasicListDto(BasicEntity basicEntity) {
        this.companyId = basicEntity.getCompanyId();
        this.companyNum = basicEntity.getCompanyNum();
        this.companyName = basicEntity.getCompanyName();
        this.companyContent = basicEntity.getCompanyContent();
        this.useYN = basicEntity.getUseYN();
    }
}

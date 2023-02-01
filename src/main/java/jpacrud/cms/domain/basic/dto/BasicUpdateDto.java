package jpacrud.cms.domain.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasicUpdateDto extends BasicDto {

    private Long companyId;
    private int companyNum;
    private String companyName;
    private String companyContent;
    private String useYN;
}

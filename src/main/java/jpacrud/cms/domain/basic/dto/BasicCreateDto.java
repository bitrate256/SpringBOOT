package jpacrud.cms.domain.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasicCreateDto {

    private Long companyId;
    private int companyNum;
    private String companyName;
    private String companyContent;
    private String useYN;
}

package jpacrud.cms.domain.company.dto;

import jpacrud.cms.global.application.StringToLocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryModel {

    private String keyword;

    private String columnName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String createId;

    private int companyLevel;

    public QueryModel(String keyword, String columnName, String startDate, String endDate, String createId, int companyLevel){
        this.keyword = keyword;
        this.columnName = columnName;
        this.startDate = StringToLocalDateTime.convertLocalDateTime(startDate);
        this.endDate = StringToLocalDateTime.convertLocalDateTime(endDate);
        this.createId = createId;
        this.companyLevel = companyLevel;
    }
}

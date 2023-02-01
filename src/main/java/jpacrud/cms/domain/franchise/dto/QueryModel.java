package jpacrud.cms.domain.franchise.dto;

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

    private String userId;

    public QueryModel(String keyword, String columnName, String startDate, String endDate, String userId){
        this.keyword = keyword;
        this.columnName = columnName;
        this.startDate = StringToLocalDateTime.convertLocalDateTime(startDate);
        this.endDate = StringToLocalDateTime.convertLocalDateTime(endDate);
        this.userId = userId;
    }
}

package jpacrud.cms.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeUpdateDto extends NoticeDto {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String noticeAuthor;
    private String noticeYn;
    private String delYn;
}

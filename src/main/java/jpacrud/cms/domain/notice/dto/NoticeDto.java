package jpacrud.cms.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeDto {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String noticeAuthor;
    private LocalDateTime noticeDateTime;
    private String noticeYn;
    private String deleteYn;
    private int view;
}

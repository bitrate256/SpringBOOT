package jpacrud.cms.domain.notice.dto;

import jpacrud.cms.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListDto {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private String noticeAuthor;
    private LocalDateTime noticeDateTime;
    private String noticeYn;
    private String delYn;
    private int view;

    public NoticeListDto(Notice notice) {
        this.id = notice.getId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.noticeAuthor = notice.getNoticeAuthor();
        this.noticeDateTime = notice.getCreateDateTime();
        this.noticeYn = notice.getNoticeYn();
        this.delYn = notice.getDelYn();
        this.view = notice.getView();
    }
}

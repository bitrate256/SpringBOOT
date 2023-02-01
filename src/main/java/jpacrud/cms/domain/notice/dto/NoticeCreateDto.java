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
public class NoticeCreateDto {

    private Long id;
    private String userId;
    private String userPw;
    private String noticeTitle;
    private String noticeContent;
    private String noticeAuthor;
    // 공통
    private LocalDateTime createDate;
    private String createId;
    private LocalDateTime modifyDate;
    private String modifyId;
    private String delYn;
//    private String noticeYn;
//    private String deleteYn;
}

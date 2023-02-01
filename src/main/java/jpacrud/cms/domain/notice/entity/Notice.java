package jpacrud.cms.domain.notice.entity;

import jpacrud.cms.domain.notice.dto.NoticeUpdateDto;
import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.notice.dto.NoticeCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "notice")
public class Notice {

    @Comment("게시물ID")
    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자seq(id) 연관관계")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

    @Comment("제목")
    @Column(name = "notice_title")
    private String noticeTitle;

    @Comment("내용")
    @Column(name = "notice_content")
    private String noticeContent;

    @Comment("작성자")
    @Column(name = "notice_author")
    private String noticeAuthor;

    @Comment("공지여부")
    @Column(name = "notice_yn")
    private String noticeYn;

    @Comment("조회수")
    @Column(name = "view", columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Comment("생성일자")
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Comment("생성한 계정의 id")
    @Column(name = "create_id", nullable = false)
    private String createId;

    @Comment("수정일자")
    @Column(name = "modify_date_time")
    private LocalDateTime modifyDateTime;

    @Comment("최종 수정한 계정의 id")
    @Column(name = "modify_id")
    private String modifyId;

    @Comment("삭제여부")
    @Column(name = "del_yn", nullable = false)
    private String delYn;

    // 생성
    public static Notice create(NoticeCreateDto dto, User user) {
//        String localDate = String.valueOf(LocalDate.now()).substring(0, 10);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return Notice.builder()
                .user(user)
//                .id(user.getId())
                .noticeTitle(dto.getNoticeTitle())
                .noticeContent(dto.getNoticeContent())
                .noticeAuthor(dto.getNoticeAuthor())
//                .noticeYn(dto.getNoticeYn())
//                .deleteYn(dto.getDeleteYn())
                .createDateTime(localDateTime)
                .createId(dto.getUserId())
                .build();
    }

    // 업데이트
    public void update(NoticeUpdateDto dto){
        this.noticeTitle = dto.getNoticeTitle();
        this.noticeContent = dto.getNoticeContent();
        this.noticeYn = dto.getNoticeYn();
        this.delYn = dto.getDelYn();
    }

    // 삭제
    public void delete() {
        this.delYn = "Y";
    }
}

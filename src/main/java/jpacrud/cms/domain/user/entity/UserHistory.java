package jpacrud.cms.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "user_history")
public class UserHistory {

    @Comment("로그id")
    @Id
    @Column(name = "log_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자id(계정 and ID값)")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;


    @Comment("로그시간")
    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Comment("생성일자")
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Comment("생성한 계정의 id")
    @Column(name = "create_id", nullable = false)
    private int createId;

    @Comment("수정일자")
    @Column(name = "modify_date_time")
    private LocalDateTime modifyDateTime;

    @Comment("최종 수정한 계정의 id")
    @Column(name = "modify_id")
    private int modifyId;

    @Comment("삭제여부")
    @Column(name = "del_yn", nullable = false)
    private String delYn;
}

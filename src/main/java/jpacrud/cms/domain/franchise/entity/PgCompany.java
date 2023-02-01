package jpacrud.cms.domain.franchise.entity;

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
@Table(name = "pg_company")
public class PgCompany {

    @Comment("PG사 ID")
    @Id
    @Column(name = "pg_company_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("PG사 이름")
    @Column(name = "pg_company_name", nullable = false)
    private String pgCompanyName;

    @Comment("생성일자")
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Comment("생성한 계정의 id")
    @Column(name = "create_id", nullable = false)
    private Long createId;

    @Comment("수정일자")
    @Column(name = "modify_date_time")
    private LocalDateTime modifyDateTime;

    @Comment("최종 수정한 계정의 id")
    @Column(name = "modify_id")
    private Long modifyId;

    @Comment("삭제여부")
    @Column(name = "del_yn", nullable = false)
    private String delYn;
}

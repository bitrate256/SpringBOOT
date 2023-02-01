package jpacrud.cms.domain.company.entity;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.company.dto.CompanyCreateDto;
import jpacrud.cms.domain.company.dto.CompanyUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@Table(name = "company")
public class Company {

    @Comment("업체코드 / Long id값")
    @Id
    @Column(name = "company_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Comment("사용자seq(id) 연관관계")
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

//     자기 자신을 self join
    // 부모 키
//    @JsonBackReference // 자식필드에 사용. 순환참조 오류 방지
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent")
    private Company parent;

    // 자식 키
//    @JsonManagedReference // 자식필드에 사용. 순환참조 오류 방지
//    @Builder.Default
//    @OneToMany(mappedBy="parent",orphanRemoval = true)
//    private List<Company> children = new ArrayList<>();

    // 하위조직들 조회가 필요할 때 사용
    //    @JsonBackReference // 자식필드에 사용. 순환참조 오류 방지
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "parent")
//    private Company parent;

    // 자식 키
//    @JsonManagedReference // 자식필드에 사용. 순환참조 오류 방지
//    @Builder.Default
//    @OneToMany(mappedBy="parent",orphanRemoval = true)
//    private List<Company> children = new ArrayList<>();

    @Comment("업체 레벨 (1: 총판 / 2: 지사 / 3: 대리점)")
    @Column(name = "company_level", nullable = false)
    private int companyLevel;

    @Comment("상위업체 Path / 상위업체코드 따라 자동 할당됨")
    @Column(name = "high_company_path", nullable = false)
    private String highCompanyPath;

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

    // 업체 생성
    public static Company create(CompanyCreateDto dto, User user) {
//        String localDate = String.valueOf(LocalDate.now()).substring(0, 10);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        return Company.builder()
                .user(user)
                .parent(dto.getParent())
                .createDateTime(localDateTime)
                .createId(user.getCreateId())
                .delYn("Y")
                .build();
    }

    public void update(CompanyUpdateDto dto, User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        // company 테이블
        this.companyLevel = dto.getCompanyLevel();
        this.parent = dto.getParent();
        this.highCompanyPath = dto.getHighCompanyPath();
        // 공통
        this.modifyId = user.getUserId();
        this.modifyDateTime = localDateTime;
        this.delYn = dto.getDelYn();
        // user 테이블
        this.user = user;
    }

    public void delete() {
        this.delYn = "Y";
    }
}
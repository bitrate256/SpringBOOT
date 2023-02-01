package jpacrud.cms.domain.terminal.entity;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.franchise.entity.Franchise;
import jpacrud.cms.domain.terminal.dto.TerminalCreateDto;
import jpacrud.cms.domain.terminal.dto.TerminalUpdateDto;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "terminal")
public class Terminal {

    @Comment("단말기id값(자동생성)")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("가맹점 코드(아이디/id)")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "franchise_code", nullable = false)
    private Franchise franchise;

    @Comment("단말기 번호 (결제 단말기 고유 ID) (단말기 식별, 삽입시 유니크하게 유효성검사 필요)")
    @Column(name = "tid", nullable = false)
    private String tid;

    @Comment("단말기 코드")
    @Column(name = "terminal_code", nullable = false)
    private String terminalCode;

    @Comment("사업자번호")
    @Column(name = "bno", nullable = false)
    private String bno;

    @Comment("단말기인증여부")
    @Column(name = "auth_yn", nullable = false)
    private String authYn;

//    @Comment("단말기 번호. 예) B 012-2560-6750")
//    @Column(name = "terminal_number", nullable = false)
//    private String terminalNumber;

    @Comment("구분 단말기/수기")
    @Column(name = "terminal_type", nullable = false)
    private String terminalType;

    @Comment("PG (미분류/웰컴/ksnet)")
    @Column(name = "terminal_pg_type", nullable = false)
    private String terminalPgType;

    @Comment("운영상태 (사용/해지)")
    @Column(name = "terminal_status", nullable = false)
    private String terminalStatus;

//    @Comment("업체명 (총판>지사>대리점)")
//    @Column(name = "company_name", nullable = false)
//    private String companyName;
//
    @Comment("대표자명")
    @Column(name = "ceo_name", nullable = false)
    private String ceoName;
//
    @Comment("담당자 연락처")
    @Column(name = "manager_contact", nullable = false)
    private String managerContact;

    @Comment("가맹점(상점)명")
    @Column(name = "franchise_name", nullable = false)
    private String franchiseName;

//    @Comment("사업자구분 (비/법/개)")
//    @Column(name = "cp_type", nullable = false)
//    private String cpType;

    @Comment("개시거래일 (개시거래 통지 목록 위함)")
    @Column(name = "transaction_start_date")
    private LocalDateTime transactionStartDate;

//    @Comment("전화번호")
//    @Column(name = "tel")
//    private LocalDateTime tel;
//
//    @Comment("주소")
//    @Column(name = "address")
//    private String address;

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

    @Comment("할부최저금액")
    @Column(name = "installment_min_amount", nullable = false)
    private Long installmentMinAmount;

    public static Terminal create(TerminalCreateDto dto, Franchise franchise) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        return Terminal.builder()
                .tid(dto.getTid())
                .franchise(franchise)
                .franchiseName(dto.getFranchiseName())
                .terminalCode(dto.getTerminalCode())
                .terminalType(dto.getTerminalType())
                .terminalPgType(dto.getTerminalPgType())
                .terminalStatus(dto.getTerminalStatus())
                .bno(dto.getBno())
                .delYn("Y")
                .authYn(dto.getAuthYn())
                .createId(dto.getCreateId())
                .createDateTime(localDateTime)
                .build();
    }

    public void update(TerminalUpdateDto dto, User user, Franchise franchise) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        // terminal 테이블
        this.tid = dto.getTid();
        this.franchise = franchise;
        this.bno = dto.getBno();
        this.authYn = dto.getAuthYn();
        this.terminalCode = dto.getTerminalCode();
        this.terminalType = dto.getTerminalType();
        this.terminalPgType = dto.getTerminalPgType();
        this.terminalStatus = dto.getTerminalStatus();
        this.franchiseName = dto.getFranchiseName();
        this.transactionStartDate = dto.getTransactionStartDate();
        // 공통
        this.modifyId = user.getUserId();
        this.modifyDateTime = localDateTime;
    }

    public void delete() {
        // terminal 테이블
        this.delYn = "Y";
    }
}

package jpacrud.cms.domain.franchise.entity;

import jpacrud.cms.domain.company.entity.Company;
import jpacrud.cms.domain.franchise.dto.FranchiseCreateDto;
import jpacrud.cms.domain.franchise.dto.FranchiseUpdateDto;
import jpacrud.cms.domain.user.entity.User;
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
@Table(name="franchise")
public class Franchise {

    @Comment("가맹점 코드 / Long id값")
    @Id
    @Column(name = "franchise_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자seq(id) 연관관계")
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_seq", nullable = false)
    private User user;

    @Comment("PG사 ID")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "pg_company_id", nullable = false)
    private PgCompany pgCompany;

    @Comment("업체코드(총판/지점/가맹점)")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "company_code", nullable = false)
    private Company company;

    @Comment("가맹점별칭")
    @Column(name = "franchise_nickname", nullable = false)
    private String franchiseNickname;

    @Comment("대표자 주민번호")
    @Column(name = "ceo_id")
    private String ceoId;

    @Comment("정산주기(1: D+1 / 2: D+3 / 3: D+4 / 4: D+5)")
    @Column(name = "settlement_cycle", nullable = false)
    private String settlementCycle;

    @Comment("결제취소 (당일 or not 당일, 체크박스)")
    @Column(name = "payment_cancel_today_yn", nullable = false)
    private String paymentCancelTodayYn;

    @Comment("정산보류(해당없음 / 보류체크)")
    @Column(name = "settlement_pending_yn", nullable = false)
    private String settlementPendingYn;

    @Comment("할부보류 (해당없음 / ? )")
    @Column(name = "installment_pending", nullable = false)
    private String installmentPending;

    @Comment("결제한도(건)")
    @Column(name = "payment_limit_foreach", nullable = false)
    private String paymentLimitForeach;

    @Comment("결제한도(월)")
    @Column(name = "payment_limit_month", nullable = false)
    private String paymentLimitMonth;

    @Comment("간편결제 허용 여부")
    @Column(name = "payment_easy_yn", nullable = false)
    private String paymentEasyYn;

    @Comment("인증여부(비인증 / 비생인증)")
    @Column(name = "auth_yn", nullable = false)
    private String authYn;

    @Comment("간편결제할부기간(드롭다운)")
    @Column(name = "payment_easy_limit_period", nullable = false)
    private String paymentEasyLimitPeriod;

    @Comment("가맹점 mid(가맹점 식별)")
    @Column(name = "mid", nullable = false)
    private String mid;

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

    // 가맹점 생성
    public static Franchise create(FranchiseCreateDto dto, PgCompany pgCompany, User user, Company company) {
//        String localDate = String.valueOf(LocalDate.now()).substring(0, 10);
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        return Franchise.builder()
                .user(user)
                .pgCompany(pgCompany)
                .company(company)
                .franchiseNickname(dto.getFranchiseNickname())
                .ceoId(dto.getCeoId())
                .settlementCycle(dto.getSettlementCycle())
                .paymentCancelTodayYn(dto.getPaymentCancelTodayYn())
                .settlementPendingYn(dto.getSettlementPendingYn())
                .installmentPending(dto.getInstallmentPending())
                .paymentLimitForeach(dto.getPaymentLimitForeach())
                .paymentLimitMonth(dto.getPaymentLimitMonth())
                .paymentEasyYn(dto.getPaymentEasyYn())
                .authYn(dto.getAuthYn())
                .paymentEasyLimitPeriod(dto.getPaymentEasyLimitPeriod())
                .mid(dto.getMid())
                .createDateTime(localDateTime)
                .createId(user.getUserId())
                .delYn("Y")
                .build();
    }

    public void update(FranchiseUpdateDto dto, User user, PgCompany pgCompany, Company company) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        // franchise 테이블
        this.user = user;
        this.pgCompany = pgCompany;
        this.company = company;
        // 공통
        this.modifyId = user.getUserId();
        this.modifyDateTime = localDateTime;
        this.delYn = dto.getDelYn();

        this.franchiseNickname = dto.getFranchiseNickname();
        this.ceoId = dto.getCeoId();
        this.settlementCycle = dto.getSettlementCycle();
        this.paymentCancelTodayYn = dto.getPaymentCancelTodayYn();
        this.settlementPendingYn = dto.getSettlementPendingYn();
        this.installmentPending = dto.getInstallmentPending();
        this.paymentLimitForeach = dto.getPaymentLimitForeach();
        this.paymentLimitMonth = dto.getPaymentLimitMonth();
        this.paymentEasyYn = dto.getPaymentEasyYn();
        this.authYn = dto.getAuthYn();
        this.paymentEasyLimitPeriod = dto.getPaymentEasyLimitPeriod();
        this.mid = dto.getMid();
    }

    public void delete() {
        this.delYn = "Y";
    }
}

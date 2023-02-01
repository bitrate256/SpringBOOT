package jpacrud.cms.domain.company.entity;

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
@Table(name = "company_settlement")
public class CompanySettle {

    @Comment("업체정산id")
    @Id
    @Column(name = "company_settlement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companySettlementId;

    @Comment("업체코드")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "company_code",  nullable = false)
    private Company company;


    @Comment("업체명")
    @Column(name = "company_name")
    private String companyName;

    @Comment("수수료율")
    @Column(name = "commission_rate",  nullable = false)
    private double commissionRate;

    @Comment("가맹점수")
    @Column(name = "franchise_count")
    private int franchiseCount;

    @Comment("결제건수")
    @Column(name = "payment_count")
    private int paymentCount;

    @Comment("승인금액")
    @Column(name = "approval_amount")
    private int approvalAmount;

    @Comment("취소금액")
    @Column(name = "cancel_amount")
    private int cancelAmount;

    @Comment("거래금액")
    @Column(name = "trading_amount")
    private int tradingAmount;

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

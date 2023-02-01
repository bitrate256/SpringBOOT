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
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="franchise_settlement")
public class FranchiseSettlement {

    @Comment("가맹점정산내역 id값")
    @Id
    @Column(name = "franchise_settlement_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("가맹점 코드")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "franchise_code", nullable = false)
    private Franchise franchise;


    @Comment("가맹점정산 가맹점명")
    @Column(name = "franchise_name", nullable = false)
    private String franchiseName;

    @Comment("가맹점정산 건수")
    @Column(name = "count")
    private int count;

    @Comment("가맹점정산 거래금액")
    @Column(name = "trading_amount")
    private int tradingAmount;

    @Comment("가맹점정산 수수료")
    @Column(name = "commission_amount")
    private int commissionAmount;

    @Comment("가맹점정산 정산금액")
    @Column(name = "settlement_amount")
    private int settlementAmount;

    @Comment("가맹점정산 취소(차감)건수")
    @Column(name = "cancel_count")
    private int cancelCount;

    @Comment("가맹점정산 취소금액")
    @Column(name = "cancel_amount")
    private int cancelAmount;

    @Comment("가맹점정산 수수료")
    @Column(name = "cancel_commission_amount")
    private int cancelCommissionAmount;

    @Comment("가맹점정산 차감할금액")
    @Column(name = "cancel_amount_expected")
    private int cancelAmountExpected;

    @Comment("가맹점정산 차감완료금액")
    @Column(name = "cancel_amount_completed")
    private int cancelAmountCompleted;

    @Comment("전체 정산차감")
    @Column(name = "total_settlement_cancel")
    private int totalSettlementCancel;

    @Comment("전체 정산금액")
    @Column(name = "total_settlement_amount")
    private int totalSettlementAmount;

    @Comment("전체 수수료")
    @Column(name = "total_commission_amount")
    private int totalCommissionAmount;

    @Comment("전체 입금금액")
    @Column(name = "total_deposit_amount")
    private int totalDepositAmount;

    @Comment("입금은행명 (드롭다운)")
    @Column(name = "account_name")
    private String accountName;

    @Comment("계좌번호")
    @Column(name = "account_number")
    private int accountNumber;

    @Comment("예금주")
    @Column(name = "account_holder")
    private String accountHolder;

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

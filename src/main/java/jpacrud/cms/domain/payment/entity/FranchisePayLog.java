package jpacrud.cms.domain.payment.entity;


import jpacrud.cms.domain.terminal.entity.Terminal;
import jpacrud.cms.domain.franchise.entity.Franchise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "franchise_payment_log")
public class FranchisePayLog {

    @Comment("결제내역id")
    @Id
    @Column(name = "payment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("가맹점 코드")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_code", nullable = false)
    private Franchise franchise;

    @Comment("단말기번호 (결제 단말기 고유 ID)")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "tid", nullable = false)
    private Terminal terminal;


    @Comment("거래일자")
    @Column(name = "trading_date")
    private LocalDateTime tradingDate;

    @Comment("결제여부 (1: 승인 / 2: 취소)")
    @Column(name = "payment_yn")
    private String paymentYn;

    @Comment("매입사")
    @Column(name = "acquirer")
    private String acquirer;

    @Comment("할부")
    @Column(name = "installment")
    private int installment;

    @Comment("카드 결제시 사용되는 카드번호")
    @Column(name = "card_no", nullable = false)
    private String cardNo;

    @Comment("카드승인번호. 취소인 경우, 승인번호와 같은 번호 사용")
    @Column(name = "approval_number")
    private int approvalNumber;

    @Comment("결제금액")
    @Column(name = "amount_money")
    private int amountMoney;

    @Comment("취소금액 (카드취소금액)")
    @Column(name = "cancel_amount")
    private int cancelAmount;

    @Comment("거래금액")
    @Column(name = "trading_amount")
    private int tradingAmount;

    @Comment("수수료")
    @Column(name = "commission_amount")
    private int commissionAmount;

    @Comment("PG")
    @Column(name = "pg_name", nullable = false)
    private String pgName;

    @Comment("Agent")
    @Column(name = "agent", nullable = false)
    private String agent;

    @Comment("전표 url")
    @Column(name = "reciept_url", nullable = false)
    private String recieptUrl;

    @Comment("원거래일자")
    @Column(name = "org_app_date")
    private LocalDateTime orgAppDate;

    @Comment("결제구분 (A : 인증 / N : 비인증)")
    @Column(name = "pay_type", nullable = false)
    private String payType;

    @Comment("결제수단 (C : 카드 / P : 현금영수증 / H : 휴대폰 / V : 가상계좌 / B : 계좌이체)")
    @Column(name = "pay_method", nullable = false)
    private String payMethod;

    @Comment("과세 여부 (00: 과세, 01: 비과세, 02: 복합과세)")
    @Column(name = "tax_free", nullable = false)
    private String taxFree;

    @Comment("면세금액")
    @Column(name = "tax_free_money", nullable = false)
    private int taxFreeMoney;

    @Comment("공급가액")
    @Column(name = "supply_money", nullable = false)
    private int supplyMoney;

    @Comment("부가세")
    @Column(name = "vat_money", nullable = false)
    private int vatMoney;

    @Comment("봉사료")
    @Column(name = "tip_money", nullable = false)
    private int tipMoney;

    @Comment("할부개월수 (00 : 일시불 / 01 : 1개월 / 02 : 2개월 / 03 : 3개월 / ...)")
    @Column(name = "in_month", nullable = false)
    private String inMonth;

    @Comment("사용자 정의 필드 1 : 응답 시 입력한 값을 회신합니다")
    @Column(name = "udf1")
    private String udf1;

    @Comment("사용자 정의 필드 2 : 응답 시 입력한 값을 회신합니다")
    @Column(name = "udf2")
    private String udf2;

    @Comment("사용자 정의 필드 3 : 응답 시 입력한 값을 회신합니다")
    @Column(name = "udf3")
    private String udf3;

    @Comment("주문거래번호 ([응답 시 회신] 가맹점에서 부여한 주문 거래번호)")
    @Column(name = "order_no")
    private String orderNo;

    @Comment("결과코드 ([0000 : 정상, 기타 : 실패])")
    @Column(name = "result_cd", nullable = false)
    private String resultCd;

    @Comment("결과메세지 (성공시: OK / 실패시: 기타 오류 메시지)")
    @Column(name = "result_msg", nullable = false)
    private String resultMsg;

    @Comment("거래타이틀")
    @Column(name = "title", nullable = false)
    private String title;

    @Comment("바코드 (바코드숫자)")
    @Column(name = "barcode")
    private String barcode;

    @Comment("카드발급사명")
    @Column(name = "card_issuer_name", nullable = false)
    private String cardIssuerName;

    @Comment("카드발급사코드")
    @Column(name = "card_issuer_code", nullable = false)
    private String cardIssuerCode;

    @Comment("카드매입사명")
    @Column(name = "card_acq_name", nullable = false)
    private String cardAcqName;

    @Comment("카드매입사코드")
    @Column(name = "card_acq_code", nullable = false)
    private String cardAcqCode;

    @Comment("영수증메세지1 (영수증에 출력되는 메시지 1. 문자열 + crlf 문자열 + crlf ..)")
    @Column(name = "content_1")
    private String content1;

    @Comment("영수증메세지2 (영수증에 출력되는 메시지 2. 상동)")
    @Column(name = "content_2")
    private String content2;

    @Comment("취소구분 (C : 일반 취소, N : 망상 취소)")
    @Column(name = "cancel_type")
    private String cancelType;

    @Comment("즉시출금여부 (C : 일반 취소, N : 망상 취소)")
    @Column(name = "immediate_yn", nullable = false)
    private String immediateYn;

    @Comment("사인정보 (고객사인정보)")
    @Column(name = "sign_data")
    private String signData;

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

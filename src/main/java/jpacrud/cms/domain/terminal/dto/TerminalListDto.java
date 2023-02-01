package jpacrud.cms.domain.terminal.dto;

import jpacrud.cms.domain.franchise.entity.Franchise;
import jpacrud.cms.domain.terminal.entity.Terminal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TerminalListDto {

    private Long id;
    private String tid; // 단말기 번호
    private Franchise franchise; // 가맹점 코드 (franchiseCode)
    private String bno; // 사업자번호 (필수값)
    private String authYn; // 단말기 인증 여부는 운영상태로 판단
    private String terminalCode; // 단말기 코드
    private String terminalType; // 구분 단말기/수기
    private String terminalPgType; // PG (미분류/웰컴/ksnet)
    private String terminalStatus; // 운영상태 (사용/해지)
    private String franchiseName; // 가맹점 명
    private LocalDateTime transactionStartDate; // 개시거래일 (운영상태 사용 등록시 날짜)
    private String ceoName;
    private String managerContact;


    // 공통
    private LocalDateTime createDate;
    private String createId;
    private LocalDateTime modifyDate;
    private String modifyId;
    private String delYn;

    public TerminalListDto(Terminal terminal) {
        this.id = terminal.getId();
        this.tid = terminal.getTid();
        this.franchise = terminal.getFranchise();
        this.bno = terminal.getBno();
        this.authYn = terminal.getAuthYn();
        this.terminalCode = terminal.getTerminalCode();
        this.terminalPgType = terminal.getTerminalPgType();
        this.terminalStatus = terminal.getTerminalStatus();
        this.franchiseName = terminal.getFranchiseName();
        this.transactionStartDate = terminal.getTransactionStartDate();
        this.ceoName = terminal.getCeoName();
        this.managerContact = terminal.getManagerContact();
        this.createDate = terminal.getCreateDateTime();
        this.createId = terminal.getCreateId();
        this.modifyDate = terminal.getModifyDateTime();
        this.modifyId = terminal.getModifyId();
    }
}

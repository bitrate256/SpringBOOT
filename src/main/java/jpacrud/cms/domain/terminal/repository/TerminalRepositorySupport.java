package jpacrud.cms.domain.terminal.repository;

import jpacrud.cms.domain.terminal.dto.QueryModel;
import jpacrud.cms.domain.terminal.dto.TerminalListDto;
import jpacrud.cms.domain.terminal.entity.QTerminal;
import jpacrud.cms.domain.terminal.entity.Terminal;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TerminalRepositorySupport {

    private final JPAQueryFactory queryFactory;

    // Tid 중복여부 확인
    public boolean existsByTid(String tid) {
        return queryFactory.selectFrom(QTerminal.terminal)
                .where(QTerminal.terminal.tid.eq(tid))
                .fetchFirst() == null;
    }

    // Tid 로 단말기 검색
    public Terminal findByTid(String tid) {
        return queryFactory.selectFrom(QTerminal.terminal)
                .where(QTerminal.terminal.tid.eq(tid))
                .fetchOne();
    }

    // id 값으로 단말기 검색 (현재는 필요 없음)
    public Terminal findById(Long id) {
        return queryFactory.selectFrom(QTerminal.terminal)
                .where(QTerminal.terminal.id.eq(id))
                .fetchOne();
    }

    // keyword 에 포함되어있으면 검색. null 이면 null 로 둠
    // keyword 로만 검색
    // tid / 단말기코드 / 업체명 / 가맹점영
    private BooleanExpression keyword(String keyword){
        return QTerminal.terminal.tid.stringValue().contains(keyword) // tid
                .or(QTerminal.terminal.terminalCode.contains(keyword)) // 단말기 코드
                .or(QTerminal.terminal.franchise.user.agentName.contains(keyword)) // 업체명
                .or(QTerminal.terminal.franchiseName.contains(keyword)); // 가맹점명
    }

    // String 변수 검색용 키워드 전달받기
    // tid / 단말기코드 / 업체명 / 가맹점영
    private BooleanExpression keywordColumnName(QueryModel queryModel){
        if(Objects.equals(queryModel.getColumnName(), "tid")) {
            return QTerminal.terminal.tid.stringValue().contains(queryModel.getKeyword()); // tid
        } else if (Objects.equals(queryModel.getColumnName(), "terminalCode")) {
            return QTerminal.terminal.terminalCode.contains(queryModel.getKeyword()); // 단말기 코드
        } else if (Objects.equals(queryModel.getColumnName(), "agentName")) {
            return QTerminal.terminal.franchise.user.agentName.contains(queryModel.getKeyword()); // 업체명
        } else if (Objects.equals(queryModel.getColumnName(), "franchiseName")) {
            return QTerminal.terminal.franchiseName.contains(queryModel.getKeyword()); // 가맹점명
        } else if (Objects.equals(queryModel.getColumnName(), "ceoName")) {
            return QTerminal.terminal.ceoName.contains(queryModel.getKeyword()); // 대표자명
        } else if (Objects.equals(queryModel.getColumnName(), "terminalPgType")) {
            return QTerminal.terminal.terminalPgType.contains(queryModel.getKeyword()); // 가맹점명
        } else if (Objects.equals(queryModel.getColumnName(), "managerContact")) {
            return QTerminal.terminal.managerContact.contains(queryModel.getKeyword()); // 담당자연락처
        }
        return null;
    }

    // 조건 없이 전체 검색
    public Page<TerminalListDto> findAllTerminal(Pageable pageable, QueryModel queryModel) {
        List<TerminalListDto> resultTerminalListDto =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
//                        .where(notice.noticeYn.eq("Y").and(notice.deleteYn.eq("Y")))
                        .where(QTerminal.terminal.delYn.eq("N"))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QTerminal.terminal.tid.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
//                        .where(notice.noticeYn.eq("Y").and(notice.deleteYn.eq("Y")))
                        .where(QTerminal.terminal.delYn.eq("N"))
                        .fetch()
                        .size();
        return new PageImpl<>(resultTerminalListDto,pageable,totalCount);
    }

    // keyword 로만 검색
    public Page<TerminalListDto> findTerminalKeyword(Pageable pageable, QueryModel queryModel) {
        List<TerminalListDto> resultTerminalListDto =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
                        .where(QTerminal.terminal.delYn.eq("N")
                                ,keyword(queryModel.getKeyword()))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QTerminal.terminal.tid.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
                        .where(QTerminal.terminal.delYn.eq("N")
                                ,keyword(queryModel.getKeyword()))
                        .fetch()
                        .size();
        return new PageImpl<>(resultTerminalListDto,pageable,totalCount);
    }

    // String 변수 검색
    public Page<TerminalListDto> findByTidCodeAgencyFranchise(Pageable pageable, QueryModel queryModel) {
        List<TerminalListDto> resultTerminalListDto =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
                        .where(QTerminal.terminal.delYn.eq("N")
                                ,keywordColumnName(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QTerminal.terminal.tid.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(TerminalListDto.class, QTerminal.terminal))
                        .from(QTerminal.terminal)
                        .where(QTerminal.terminal.delYn.eq("N")
                                ,keywordColumnName(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultTerminalListDto,pageable,totalCount);
    }


}

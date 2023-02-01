package jpacrud.cms.domain.franchise.repository;

import jpacrud.cms.domain.franchise.entity.QFranchise;
import jpacrud.cms.domain.franchise.dto.QueryModel;
import jpacrud.cms.domain.franchise.entity.Franchise;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static jpacrud.cms.domain.company.entity.QCompany.company;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public boolean existsByUserId(String userId) {
        return queryFactory.selectFrom(QFranchise.franchise)
                .where(QFranchise.franchise.user.userId.eq(userId))
                .fetchFirst() != null;

    }

    public Franchise findByFranchiseCode(Long id) {
        return queryFactory.selectFrom(QFranchise.franchise)
                .where(QFranchise.franchise.id.eq(id))
                .fetchOne();
    }

    // keyword 에 포함되어있으면 검색. null 이면 null 로 둠
    // keyword 로만 검색
    // 현재는 업체명/가맹점명/대표자명/사용자ID
    private BooleanExpression keyword(String keyword){
        return QFranchise.franchise.user.agentName.contains(keyword)
                .or(QFranchise.franchise.user.ceoName.contains(keyword))
                .or(QFranchise.franchise.user.managerName.contains(keyword))
                .or(QFranchise.franchise.user.userId.contains(keyword));
    }

    // String 변수 검색용 키워드 전달받기
    private BooleanExpression keywordColumnName(QueryModel queryModel){
        if(Objects.equals(queryModel.getColumnName(), "agentName")) {
            return QFranchise.franchise.user.agentName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "ceoName")) {
            return QFranchise.franchise.user.ceoName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "managerName")) {
            return QFranchise.franchise.user.managerName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "userId")) {
            return QFranchise.franchise.user.userId.contains(queryModel.getKeyword());
        }
        return null;
    }

    // 날짜 사이값 구하기
    private BooleanExpression betweenDate(QueryModel queryModel){
        if(queryModel.getStartDate() == null || queryModel.getEndDate() == null) {
            return null;
        } else {
            // 엔드데이트에 23시간 59분 59초를 더하여 오늘까지 날짜로 가정
            LocalDateTime endDate = queryModel.getEndDate().plusHours(23).plusMinutes(59).plusSeconds(59);
            return  QFranchise.franchise.createDateTime.between(queryModel.getStartDate(),endDate);
        }
    }

    // 조건 없이 전체 검색
    public Page<Franchise> findAllFranchise(Pageable pageable, QueryModel queryModel) {
        List<Franchise> resultListDto =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N"))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QFranchise.franchise.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N"))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }

    // keyword 로만 검색
    public Page<Franchise> findFranchiseKeyword(Pageable pageable, QueryModel queryModel) {
        List<Franchise> resultListDto =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QFranchise.franchise.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }

    // String 변수 검색
    public Page<Franchise> findByFranchiseSelectedKeyword(Pageable pageable, QueryModel queryModel) {
        List<Franchise> resultListDto =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QFranchise.franchise.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(QFranchise.franchise)
                        .join(QFranchise.franchise.company, company)
                        .where(QFranchise.franchise.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }
}

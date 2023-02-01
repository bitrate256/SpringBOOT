package jpacrud.cms.domain.basic.repository;

import jpacrud.cms.domain.basic.dto.BasicListDto;
import jpacrud.cms.domain.basic.entity.QBasicEntity;
import jpacrud.cms.domain.basic.dto.QueryModel;
import com.querydsl.core.types.Projections;
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

@Repository
@RequiredArgsConstructor
public class BasicRepositorySupport {

    private final JPAQueryFactory queryFactory;

    // keyword 에 포함되어있으면 검색. null 이면 null 로 둠
    // keyword 로만 검색
    private BooleanExpression keyword(String keyword){
//        if(keyword == null) return null;
//        if(keyword.equals("")) return null;
        return QBasicEntity.basicEntity.companyName.contains(keyword)
                .or(QBasicEntity.basicEntity.companyContent.contains(keyword))
                .or(QBasicEntity.basicEntity.companyNum.stringValue().contains(keyword));
    }

    // 해당 컬럼에 null 이 있는지 체크
    public Boolean exist(QueryModel queryModel) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(QBasicEntity.basicEntity)
                .where(QBasicEntity.basicEntity.companyNum.stringValue().contains(queryModel.getKeyword()))
                .fetchFirst();

        return fetchOne != null;
    }
    public Boolean existName(QueryModel queryModel) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(QBasicEntity.basicEntity)
                .where(QBasicEntity.basicEntity.companyName.contains(queryModel.getKeyword()))
                .fetchFirst();

        return fetchOne != null;
    }
    public Boolean existContent(QueryModel queryModel) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(QBasicEntity.basicEntity)
                .where(QBasicEntity.basicEntity.companyContent.contains(queryModel.getKeyword()))
                .fetchFirst();

        return fetchOne != null;
    }

    // String 변수 검색용 키워드 전달받기
    private BooleanExpression keywordColumnName(QueryModel queryModel){
        if(Objects.equals(queryModel.getColumnName(), "companyName")) {
            return QBasicEntity.basicEntity.companyName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "companyContent")) {
            return QBasicEntity.basicEntity.companyContent.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "companyNum")) {
            return QBasicEntity.basicEntity.companyNum.stringValue().contains(queryModel.getKeyword());
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
            return  QBasicEntity.basicEntity.signUpDateTime.between(queryModel.getStartDate(),endDate);
        }
    }

    // 조건 없이 전체 검색
    public Page<BasicListDto> findAllCompany(Pageable pageable, QueryModel queryModel) {
        List<BasicListDto> resultBasicListDto =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QBasicEntity.basicEntity.companyId.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultBasicListDto,pageable,totalCount);
    }

    // keyword 로만 검색
    public Page<BasicListDto> findCompanyKeyword(Pageable pageable, QueryModel queryModel) {
        List<BasicListDto> resultBasicListDto =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QBasicEntity.basicEntity.companyId.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultBasicListDto,pageable,totalCount);
    }

    // String 변수 검색
    public Page<BasicListDto> findByCompanyNumNameContent(Pageable pageable, QueryModel queryModel) {
        List<BasicListDto> resultBasicListDto =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(QBasicEntity.basicEntity.companyId.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(BasicListDto.class, QBasicEntity.basicEntity))
                        .from(QBasicEntity.basicEntity)
                        .where(QBasicEntity.basicEntity.useYN.eq("Y")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultBasicListDto,pageable,totalCount);
    }

}

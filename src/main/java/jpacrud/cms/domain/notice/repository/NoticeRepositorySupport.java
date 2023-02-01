package jpacrud.cms.domain.notice.repository;

import jpacrud.cms.domain.notice.dto.NoticeListDto;
import jpacrud.cms.domain.notice.dto.QueryModel;
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

import static jpacrud.cms.domain.notice.entity.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class NoticeRepositorySupport {

    private final JPAQueryFactory queryFactory;

    // keyword 에 포함되어있으면 검색. null 이면 null 로 둠
    // keyword 로만 검색
    private BooleanExpression keyword(String keyword){
        return notice.noticeTitle.contains(keyword)
                .or(notice.noticeContent.contains(keyword))
                .or(notice.noticeAuthor.contains(keyword));
    }

    // String 변수 검색용 키워드 전달받기
    private BooleanExpression keywordColumnName(QueryModel queryModel){
        if(Objects.equals(queryModel.getColumnName(), "noticeTitle")) {
            return notice.noticeTitle.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "noticeContent")) {
            return notice.noticeContent.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "noticeAuthor")) {
            return notice.noticeAuthor.contains(queryModel.getKeyword());
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
            return  notice.createDateTime.between(queryModel.getStartDate(),endDate);
        }
    }

    // 조건 없이 전체 검색
    public Page<NoticeListDto> findAllNotice(Pageable pageable, QueryModel queryModel) {
        List<NoticeListDto> resultNoticeListDto =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
//                        .where(notice.noticeYn.eq("Y").and(notice.deleteYn.eq("Y")))
                        .where(notice.delYn.eq("N"))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(notice.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
//                        .where(notice.noticeYn.eq("Y").and(notice.deleteYn.eq("Y")))
                        .where(notice.delYn.eq("N"))
                        .fetch()
                        .size();
        return new PageImpl<>(resultNoticeListDto,pageable,totalCount);
    }

    // keyword 로만 검색
    public Page<NoticeListDto> findNoticeKeyword(Pageable pageable, QueryModel queryModel) {
        List<NoticeListDto> resultNoticeListDto =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
                        .where(notice.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(notice.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
                        .where(notice.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultNoticeListDto,pageable,totalCount);
    }

    // String 변수 검색
    public Page<NoticeListDto> findByNoticeAuthorNameContent(Pageable pageable, QueryModel queryModel) {
        List<NoticeListDto> resultNoticeListDto =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
                        .where(notice.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(notice.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.select(Projections.constructor(NoticeListDto.class, notice))
                        .from(notice)
                        .where(notice.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultNoticeListDto,pageable,totalCount);
    }

}

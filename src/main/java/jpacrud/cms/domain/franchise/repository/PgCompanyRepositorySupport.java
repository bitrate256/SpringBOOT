package jpacrud.cms.domain.franchise.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PgCompanyRepositorySupport {

    private final JPAQueryFactory queryFactory;
}

package jpacrud.cms.domain.user.repository;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.entity.QUser;
import jpacrud.cms.global.error.exception.BusinessException;
import jpacrud.cms.global.error.model.ErrorCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class UserRepositorySupport {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(UserRepositorySupport.class);

    private final JPAQueryFactory queryFactory;

    // notice 작성시 작성자 위한 user 찾기
    public User findByUserId(String userId) {
        logger.debug("userId 오는지? {}",userId);
        User resultUser = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();
        if(resultUser == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        }
        return resultUser;
    }

    public User findById(String userId){

        User resultUser = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();
        if(resultUser == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        }
        return resultUser;
    }

    public User findByLoginId(String userId){

        logger.debug("findByUserId 들어옴. userId는? {}", userId);

        User user = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        }
        return user;
    }

    public User findByLoginIdAndStateY(String userId){

        logger.debug("findByLoginIdAndStateY 들어옴. userId는? {}", userId);

        User resultUser = queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.userId.eq(userId))
                .fetchOne();

        if(resultUser == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        }
        if(resultUser.getLoginAllowCheck().equals('2')){
            throw new BusinessException(ErrorCode.NOT_ACCESS_USER);
        }
        logger.debug("resultUser는? {}", resultUser);
        return resultUser;
    }
}

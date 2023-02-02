package jpacrud.cms.domain.user.repository;

import jpacrud.cms.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    // UserId 존재 여부 판단.
    // 중복되는 경우 true, 중복되지 않는 경우 false 리턴 됨.
    // 다음과 같이 Repository 에 간단하게 메소드를 추가할 수 있음.
    Boolean existsByUserId(String userId);

//    Optional<User> findByLoginId(String id);
}

package jpacrud.cms.domain.user.repository;

import jpacrud.cms.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

//    Optional<User> findByLoginId(String id);
}

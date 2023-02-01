package jpacrud.cms.domain.basic.repository;

import jpacrud.cms.domain.basic.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicRepository extends JpaRepository<BasicEntity,Long> {
}

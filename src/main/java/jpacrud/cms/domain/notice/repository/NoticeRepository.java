package jpacrud.cms.domain.notice.repository;

import jpacrud.cms.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}

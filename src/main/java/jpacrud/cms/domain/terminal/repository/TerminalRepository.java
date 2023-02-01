package jpacrud.cms.domain.terminal.repository;

import jpacrud.cms.domain.terminal.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal,Long> {

    boolean existsByTid(String tid);
}

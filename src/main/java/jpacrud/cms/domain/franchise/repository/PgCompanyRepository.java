package jpacrud.cms.domain.franchise.repository;

import jpacrud.cms.domain.franchise.entity.PgCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgCompanyRepository extends JpaRepository<PgCompany,Long> {
}

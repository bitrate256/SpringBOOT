package jpacrud.cms.domain.company.repository;

import jpacrud.cms.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}

package jpacrud.cms.domain.franchise.repository;

import jpacrud.cms.domain.franchise.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise,Long> {
}

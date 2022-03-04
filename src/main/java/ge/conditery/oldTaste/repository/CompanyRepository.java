package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company getCompanyByCompanyName(String companyName);
}

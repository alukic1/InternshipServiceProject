package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllByCompanyId(Long companyId);
    List<Internship> findByTechnologiesContains(String technologies);
}

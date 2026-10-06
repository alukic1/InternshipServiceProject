package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CvRepository extends JpaRepository<Cv, Long> {
    Optional<Cv> findByStudentId(Long id);
}

package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByEmail(String email);

}

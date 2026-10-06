package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFacultyId(Long facultyId);
    Optional<Student> findByEmail(String email);
}

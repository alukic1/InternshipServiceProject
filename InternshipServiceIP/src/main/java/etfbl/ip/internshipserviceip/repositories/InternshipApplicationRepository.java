package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.ApplicationStatus;
import etfbl.ip.internshipserviceip.entities.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Long> {
    List<InternshipApplication> findAllByStudentId(Long studentId);
    List<InternshipApplication> findAllByInternshipIdAndStudentId(Long internshipId, Long studentId);
    List<InternshipApplication> findAllByInternshipId(Long internshipId);
    List<InternshipApplication> findAllByStudentIdAndStatus(Long studentId, ApplicationStatus status);
    List<InternshipApplication> findAllByInternshipIdAndStatus(Long internshipId, ApplicationStatus status);
}

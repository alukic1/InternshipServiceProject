package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Review;
import etfbl.ip.internshipserviceip.entities.ReviewerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStudentId(Long studentId);
    List<Review> findAllByStudentIdAndReviewerType(Long studentId, ReviewerType reviewerType);
}

package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.entities.Review;
import etfbl.ip.internshipserviceip.entities.ReviewerType;
import etfbl.ip.internshipserviceip.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByStudentId(Long studentId){
        return reviewRepository.findAllByStudentId(studentId);
    }

    public List<Review> getCompanyReviewsByStudentId(Long studentId){
        return reviewRepository.findAllByStudentIdAndReviewerType(studentId, ReviewerType.COMPANY);
    }
}

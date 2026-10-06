package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.dtos.PostApplication;
import etfbl.ip.internshipserviceip.entities.ApplicationStatus;
import etfbl.ip.internshipserviceip.entities.InternshipApplication;
import etfbl.ip.internshipserviceip.repositories.InternshipApplicationRepository;
import etfbl.ip.internshipserviceip.repositories.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipApplicationService {

    @Autowired
    private InternshipApplicationRepository internshipApplicationRepository;
    @Autowired
    private InternshipRepository internshipRepository;

    public List<InternshipApplication> getAllInternshipApplicationsByStudentId(Long studentId) {
        return internshipApplicationRepository.findAllByStudentId(studentId);
    }
    public List<InternshipApplication> getInternshipApplicationByInternshipId(Long internshipId) {
        return internshipApplicationRepository.findAllByInternshipId(internshipId);
    }
    public List<InternshipApplication> getInternshipApplicationByInternshipIdAndStudentId(Long internshipId, Long studentId) {
        return internshipApplicationRepository.findAllByInternshipIdAndStudentId(internshipId, studentId);
    }
    public List<InternshipApplication> getAcceptedApplicationsByStudentId(Long studentId) {
        return internshipApplicationRepository.findAllByStudentIdAndStatus(studentId, ApplicationStatus.ACCEPTED);
    }

    public InternshipApplication applyForInternship(PostApplication postApplication) {
        InternshipApplication internshipApplication = null;
        if(internshipApplicationRepository.findAllByInternshipIdAndStudentId(postApplication.getInternshipId(), postApplication.getStudentId()).isEmpty()){
            internshipApplication = new InternshipApplication(postApplication);
            internshipApplicationRepository.save(internshipApplication);
        } //dodati izuzetak ako je prijavljen vec
        return internshipApplication;
    }

    public InternshipApplication acceptForInternship(Long applicationId) {
        InternshipApplication internshipApplication = internshipApplicationRepository.findById(applicationId).get();
        internshipApplication.setStatus(ApplicationStatus.ACCEPTED);
        internshipApplicationRepository.save(internshipApplication);
        return internshipApplication;
    }

    public InternshipApplication rejectForInternship(Long applicationId) {
        InternshipApplication internshipApplication = internshipApplicationRepository.findById(applicationId).get();
        internshipApplication.setStatus(ApplicationStatus.REJECTED);
        internshipApplicationRepository.save(internshipApplication);
        return internshipApplication;
    }
}

package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.entities.Internship;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.repositories.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public List<Internship> getInternshipsByCompanyId(Long companyId) {
        return internshipRepository.findAllByCompanyId(companyId);
    }
    public Internship getInternshipById(Long id) throws ResourceNotFoundException {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Internship not found - id: " + id));
        return internship;
    }

    public Internship createInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    public Internship updateInternship(Long id, Internship internship) {
        Internship internshipById = internshipRepository.findById(id).get();
        internshipById.setName(internship.getName());
        internshipById.setDescription(internship.getDescription());
        internshipById.setCompany(internship.getCompany());
        internshipById.setRequirements(internship.getRequirements());
        internshipById.setTechnologies(internship.getTechnologies());
        internshipById.setStartDate(internship.getStartDate());
        internshipById.setEndDate(internship.getEndDate());
        return internshipRepository.save(internshipById);
    }

    public void deleteInternshipById(Long id) throws ResourceNotFoundException {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found - id: " + id));
        internshipRepository.delete(internship);
    }


}

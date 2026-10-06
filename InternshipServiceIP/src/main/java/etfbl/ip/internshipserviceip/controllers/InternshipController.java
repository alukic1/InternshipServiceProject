package etfbl.ip.internshipserviceip.controllers;

import etfbl.ip.internshipserviceip.dtos.ComparedInternship;
import etfbl.ip.internshipserviceip.dtos.PostApplication;
import etfbl.ip.internshipserviceip.entities.Cv;
import etfbl.ip.internshipserviceip.entities.Internship;
import etfbl.ip.internshipserviceip.entities.InternshipApplication;
import etfbl.ip.internshipserviceip.services.AiRecommendationService;
import etfbl.ip.internshipserviceip.services.InternshipApplicationService;
import etfbl.ip.internshipserviceip.services.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {
    @Autowired
    private InternshipService internshipService;

    @Autowired
    private AiRecommendationService aiRecommendationService;
    @Autowired
    private InternshipApplicationService internshipApplicationService;

    @GetMapping
    public ResponseEntity<List<Internship>> getAllInternships() {
        List<Internship> internships = internshipService.getAllInternships();
        return ResponseEntity.ok(internships);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternshipById(id));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Internship>> getInternshipByCompanyId(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternshipsByCompanyId(id));
    }
    @PostMapping
    public ResponseEntity<Internship> createInternship(@RequestBody Internship internship) {
        Internship createdInternship = internshipService.createInternship(internship);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInternship);
    }

    @PutMapping("{id}")
    public ResponseEntity<Internship> updateInternship(@PathVariable Long id,@RequestBody Internship internship) {
        Internship updatedInternship = internshipService.updateInternship(id, internship);
        return ResponseEntity.ok(updatedInternship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternshipById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recommended")
    public ResponseEntity<List<ComparedInternship>> getRecommendedInternships(@RequestParam Long studentId) {
        List<ComparedInternship> comparedInternships = aiRecommendationService.getRecommendation(studentId);
        return ResponseEntity.ok(comparedInternships);
    }

    @PostMapping("/apply")
    public ResponseEntity<InternshipApplication> applyInternship(@RequestBody PostApplication postApplication) {
        InternshipApplication internshipApplication = internshipApplicationService.applyForInternship(postApplication);
        return ResponseEntity.ok(internshipApplication);
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<List<InternshipApplication>> getInternshipApplications(@PathVariable Long id) {
        List<InternshipApplication> internshipApplicationList = internshipApplicationService.getInternshipApplicationByInternshipId(id);
        return ResponseEntity.ok(internshipApplicationList);
    }

    @GetMapping("/applied")
        public ResponseEntity<List<InternshipApplication>> getAppliedInternshipsByStudentId(@RequestParam Long studentId) {
        List<InternshipApplication> internshipApplicationList = internshipApplicationService.getAllInternshipApplicationsByStudentId(studentId);
        return ResponseEntity.ok(internshipApplicationList);
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<InternshipApplication>> getAcceptedInternshipsByStudentId(@RequestParam Long studentId) {
        List<InternshipApplication> internshipApplicationList = internshipApplicationService.getAcceptedApplicationsByStudentId(studentId);
        return ResponseEntity.ok(internshipApplicationList);
    }

    @PutMapping("/application/{id}/accept")
    public ResponseEntity<InternshipApplication> acceptInternship(@PathVariable Long id) {
        InternshipApplication accepted = internshipApplicationService.acceptForInternship(id);
        return ResponseEntity.ok(accepted);
    }

    @PutMapping("/applications/{id}/reject")
    public ResponseEntity<InternshipApplication> rejectInternship(@PathVariable Long id) {
        InternshipApplication rejected = internshipApplicationService.rejectForInternship(id);
        return ResponseEntity.ok(rejected);
    }
}

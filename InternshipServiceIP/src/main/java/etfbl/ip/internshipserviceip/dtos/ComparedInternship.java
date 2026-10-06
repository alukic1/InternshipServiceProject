package etfbl.ip.internshipserviceip.dtos;

import etfbl.ip.internshipserviceip.entities.Company;
import etfbl.ip.internshipserviceip.entities.Internship;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComparedInternship {
    private Long internshipId;
    private double suitabilityScore;
    private String explanation;
    private String name;
    private String description;
    private String technologies;
    private LocalDate startDate;
    private LocalDate endDate;
    private String requirements;
    private Company company;

    public ComparedInternship(Long internshipId, double suitabilityScore, String explanation){
        this.internshipId = internshipId;
        this.suitabilityScore = suitabilityScore;
        this.explanation = explanation;
    }
    public void setInternshipData(Internship internship) {
        name = internship.getName();
        description = internship.getDescription();
        technologies = internship.getTechnologies();
        startDate = internship.getStartDate();
        endDate = internship.getEndDate();
        requirements = internship.getRequirements();
        company = internship.getCompany();
    }
}

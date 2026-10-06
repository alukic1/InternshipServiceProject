package etfbl.ip.internshipserviceip.entities;

import etfbl.ip.internshipserviceip.dtos.PostApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "internship_application")
public class InternshipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinternship_application", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private etfbl.ip.internshipserviceip.entities.Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    public InternshipApplication() {}
    public InternshipApplication(PostApplication postApplication) {
        this.student = new etfbl.ip.internshipserviceip.entities.Student();
        this.internship = new Internship();
        this.student.setId(postApplication.getStudentId());
        this.internship.setId(postApplication.getInternshipId());
        this.status = ApplicationStatus.PENDING;
    }
}
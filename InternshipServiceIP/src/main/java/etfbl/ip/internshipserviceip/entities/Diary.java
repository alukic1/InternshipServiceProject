package etfbl.ip.internshipserviceip.entities;

import etfbl.ip.internshipserviceip.dtos.PostDiary;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddiary", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private etfbl.ip.internshipserviceip.entities.Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "internship_id", nullable = false)
    private etfbl.ip.internshipserviceip.entities.Internship internship;

    @Column(name = "week_number", nullable = false)
    private Integer weekNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Lob
    @Column(name = "description")
    private String description;

    public Diary() {}
    public Diary(PostDiary postDiary) {
        this.student = new etfbl.ip.internshipserviceip.entities.Student();
        this.internship = new etfbl.ip.internshipserviceip.entities.Internship();
        this.student.setId(postDiary.getStudentId());
        this.internship.setId(postDiary.getInternshipId());
        this.weekNumber = postDiary.getWeekNumber();
        this.startDate = postDiary.getStartDate();
        this.endDate = postDiary.getEndDate();
        this.description = postDiary.getDescription();

    }
}
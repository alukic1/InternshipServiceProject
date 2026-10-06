package etfbl.ip.internshipserviceip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreview", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private etfbl.ip.internshipserviceip.entities.Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "internship_id", nullable = false)
    private Internship internship;

    @Enumerated(EnumType.STRING)
    @Column(name = "reviewer_type", nullable = false)
    private ReviewerType reviewerType;

    @Column(name = "reviewer_id", nullable = false)
    private Long reviewerId;

    @Column(name = "grade")
    private Integer grade;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "created")
    private LocalDateTime created;

}
package etfbl.ip.internshipserviceip.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "internship")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinternship", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "technologies")
    private String technologies;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Lob
    @Column(name = "requirements")
    private String requirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Internship id: " + id + "\n");
        sb.append("Name: " + name + "\n");
        sb.append("Company: " + company + "\n");
        sb.append("Description: " + description + "\n");
        sb.append("Technologies: " + technologies + "\n");
        sb.append("Start: " + startDate + "\n");
        sb.append("End: " + endDate + "\n");
        sb.append("Requirements: " + requirements + "\n\n");
        return sb.toString();
    }
}
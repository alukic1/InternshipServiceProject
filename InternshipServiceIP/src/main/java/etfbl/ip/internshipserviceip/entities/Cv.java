package etfbl.ip.internshipserviceip.entities;

import etfbl.ip.internshipserviceip.dtos.PostCv;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
@Entity
@Table(name = "cv")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcv", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private etfbl.ip.internshipserviceip.entities.Student student;

    @Lob
    @Column(name = "summary")
    private String summary;

    @Lob
    @Column(name = "education")
    private String education;

    @Lob
    @Column(name = "experience")
    private String experience;

    @Lob
    @Column(name = "skills")
    private String skills;

    @Lob
    @Column(name = "languages")
    private String languages;

    @Lob
    @Column(name = "interests")
    private String interests;

    @Lob
    @Column(name = "projects")
    private String projects;

    @Lob
    @Column(name = "picture", columnDefinition = "LONGBLOB")
    private byte[] picture;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name:" + student.getFullname() + "\n");
        sb.append("Summary: " + summary + "\n");
        sb.append("Education: " + education + "\n");
        sb.append("Experience: " + experience + "\n");
        sb.append("Skills: " + skills + "\n");
        sb.append("Languages: " + languages + "\n");
        sb.append("Interests: " + interests + "\n");
        sb.append("Projects: " + projects + "\n");
        return sb.toString();
    }

    public Cv(){}
    public Cv(PostCv postCv) {
        this.student = new Student();
        this.student.setId(postCv.getStudentId());
        this.summary = postCv.getSummary();
        this.education = postCv.getEducation();
        this.experience = postCv.getExperience();
        this.skills = postCv.getSkills();
        this.languages = postCv.getLanguages();
        this.interests = postCv.getInterests();
        this.projects = postCv.getProjects();

        if(postCv.getPictureBase64() != null){
            String base64 = postCv.getPictureBase64().split(",")[1];
            byte[] pic = Base64.getDecoder().decode(base64);
            this.picture = pic;
        }
    }
}
package ip.is.facultyapp.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6861200733832877064L;

	private Long id;

    private Student student;

    private Internship internship;

    private ReviewerType reviewerType;

    private Long reviewerId;

    private Integer grade;

    private String comment;

    private String created;

    
    
	public Review() {
		super();
	}



	public Review(Long id, Student student, Internship internship, ReviewerType reviewerType, Long reviewerId,
			Integer grade, String comment, String created) {
		super();
		this.id = id;
		this.student = student;
		this.internship = internship;
		this.reviewerType = reviewerType;
		this.reviewerId = reviewerId;
		this.grade = grade;
		this.comment = comment;
		this.created = created;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public Internship getInternship() {
		return internship;
	}



	public void setInternship(Internship internship) {
		this.internship = internship;
	}



	public ReviewerType getReviewerType() {
		return reviewerType;
	}



	public void setReviewerType(ReviewerType reviewerType) {
		this.reviewerType = reviewerType;
	}



	public Long getReviewerId() {
		return reviewerId;
	}



	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}



	public Integer getGrade() {
		return grade;
	}



	public void setGrade(Integer grade) {
		this.grade = grade;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public String getCreated() {
		return created;
	}



	public void setCreated(String created) {
		this.created = created;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

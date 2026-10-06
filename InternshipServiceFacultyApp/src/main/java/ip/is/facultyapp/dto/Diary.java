package ip.is.facultyapp.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Diary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2313492121989057504L;
	private Long id;
	private Student student;
	private Internship internship;
	private Integer weekNumber;
	private String startDate;
    private String endDate;
    private String description;
    
    
	public Diary() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Diary(Long id, Student student, Internship internship, Integer weekNumber, String startDate,
			String endDate, String description) {
		super();
		this.id = id;
		this.student = student;
		this.internship = internship;
		this.weekNumber = weekNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
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


	public Integer getWeekNumber() {
		return weekNumber;
	}


	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

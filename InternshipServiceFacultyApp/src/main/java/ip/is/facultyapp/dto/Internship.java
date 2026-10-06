package ip.is.facultyapp.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Internship implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7480952005333260665L;

	private Long id;

    private String name;

    private String description;

    private String technologies;

    private String startDate;

    private String endDate;

    private String requirements;

    private Company company;

    
    
    
	public Internship(Long id) {
		super();
		this.id = id;
	}

	public Internship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
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

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

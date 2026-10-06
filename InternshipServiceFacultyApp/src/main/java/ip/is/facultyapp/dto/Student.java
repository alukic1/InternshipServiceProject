package ip.is.facultyapp.dto;

import java.io.Serializable;

public class Student implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8487058556975009485L;

	private Long id;

    private String fullname;

    private String email;

    private String password;

    private Faculty faculty;
    
    
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String fullname, String email, String password, Faculty faculty) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}

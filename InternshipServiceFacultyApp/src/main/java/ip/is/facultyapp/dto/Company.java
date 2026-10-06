package ip.is.facultyapp.dto;

import java.io.Serializable;

public class Company implements Serializable{
    
	private static final long serialVersionUID = 3975009966322637710L;

	private Long id;

    private String name;

    private String email;

    private String password;

    private Boolean active;

    
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Company(Long id, String name, String email, String password, Boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

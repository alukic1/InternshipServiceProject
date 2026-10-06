package ip.is.facultyapp.dto;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516593101327392276L;
	private String email;
	private String password;
	public AuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
}

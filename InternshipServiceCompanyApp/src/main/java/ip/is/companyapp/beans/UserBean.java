package ip.is.companyapp.beans;

import java.io.Serializable;

public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long companyId; 
	private String companyName;
	private String companyEmail;
	private String password;
	private boolean loggedIn = false;
	
    public Long getCompanyId() { 
    	return companyId; 
   	}
    public void setCompanyId(Long companyId) { 
    	this.companyId = companyId; 
    }
    
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
    
    
}

package ip.is.facultyapp.beans;

import java.io.Serializable;

import ip.is.facultyapp.dao.FacultyDAO;
import ip.is.facultyapp.dto.AuthenticationRequest;
import ip.is.facultyapp.dto.Faculty;

public class FacultyBean implements Serializable{

	private static final long serialVersionUID = 2573714625001396621L;

	public Faculty login(AuthenticationRequest request) {
		return FacultyDAO.login(request);
	}
	
}

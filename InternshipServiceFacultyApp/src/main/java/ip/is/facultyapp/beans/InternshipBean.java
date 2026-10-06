package ip.is.facultyapp.beans;

import java.io.Serializable;
import java.util.List;

import ip.is.facultyapp.dao.InternshipDAO;
import ip.is.facultyapp.dto.Internship;

public class InternshipBean implements Serializable  {

	private static final long serialVersionUID = -287310922809197513L;

	public List<Internship> getInternships(){
		return InternshipDAO.findAll();
	}
}

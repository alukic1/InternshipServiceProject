package ip.is.facultyapp.beans;

import java.io.Serializable;
import java.util.List;

import ip.is.facultyapp.dao.CompanyDAO;
import ip.is.facultyapp.dto.Company;

public class CompanyBean implements Serializable {

	private static final long serialVersionUID = 5407279357649910576L;

	public List<Company> getCompanies(){
		return CompanyDAO.findAll();
	}
	
	public Company getCompanyById(Long id) {
		return CompanyDAO.findById(id);
	}
	
	public void addCompany(Company company) {
		CompanyDAO.addCompany(company);
	}
	
	public void updateCompany(Company company) {
		CompanyDAO.updateCompany(company);
	}
	
	public void deleteCompany(Long id) {
		CompanyDAO.deleteCompany(id);
	}
	
	public void toggleActivation(Long id) {
		CompanyDAO.toggleActivation(id);
	}
}

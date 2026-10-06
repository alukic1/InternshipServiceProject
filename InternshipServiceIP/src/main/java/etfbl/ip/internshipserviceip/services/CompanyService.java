package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.entities.Company;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found - id: " + id));
        return company;
    }
    public Company addCompany(Company company) {
        company.setPassword(authenticationService.hashPassword(company.getPassword()));
        return companyRepository.save(company);
    }

    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    public Company updateCompany(Company company) {
        Company companyById = companyRepository.findById(company.getId()).get();
        companyById.setName(company.getName());
        return companyRepository.save(companyById);
    }

    public Company toggleActivation(Long id) {
        Company company = companyRepository.findById(id).get();
        company.setActive(!company.getActive());
        return companyRepository.save(company);
    }
}

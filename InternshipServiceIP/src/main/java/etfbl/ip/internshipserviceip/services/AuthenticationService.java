package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.dtos.AuthenticationRequest;
import etfbl.ip.internshipserviceip.entities.Company;
import etfbl.ip.internshipserviceip.entities.Faculty;
import etfbl.ip.internshipserviceip.entities.Student;
import etfbl.ip.internshipserviceip.repositories.CompanyRepository;
import etfbl.ip.internshipserviceip.repositories.FacultyRepository;
import etfbl.ip.internshipserviceip.repositories.StudentRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;

    public AuthenticationService(FacultyRepository facultyRepository, StudentRepository studentRepository, CompanyRepository companyRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
    }

    public Faculty authenticateFaculty(AuthenticationRequest authenticationRequest) {
        var optional = facultyRepository.findByEmail(authenticationRequest.getEmail());
        if(optional.isEmpty()) {
            return null;
        }
        Faculty faculty = optional.get();
        if(checkPassword(authenticationRequest.getPassword(), faculty.getPassword())) {
            return faculty;
        }
        return null;
    }

    public Student authenticateStudent(AuthenticationRequest authenticationRequest) {

        var optional = studentRepository.findByEmail(authenticationRequest.getEmail());
        if(optional.isEmpty()) {
            return null;
        }
        Student student = optional.get();
        if(checkPassword(authenticationRequest.getPassword(), student.getPassword())) {
            return student;
        }
        return null;
    }

    public Company authenticateCompany(AuthenticationRequest authenticationRequest) {

        var optional = companyRepository.findByEmail(authenticationRequest.getEmail());
        if(optional.isEmpty()) {
            return null;
        }
        Company company = optional.get();
        if(checkPassword(authenticationRequest.getPassword(), company.getPassword())) {
            return company;
        }
        return null;
    }

    public Company changeCompanyPassword(AuthenticationRequest authenticationRequest) {
        var optional = companyRepository.findByEmail(authenticationRequest.getEmail());
        if(optional.isEmpty()) {
            return null;
        }
        Company company = optional.get();
        company.setPassword(BCrypt.hashpw(authenticationRequest.getPassword(), BCrypt.gensalt()));
        return companyRepository.save(company);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }


}

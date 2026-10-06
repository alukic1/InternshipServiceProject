package etfbl.ip.internshipserviceip.controllers;

import etfbl.ip.internshipserviceip.dtos.AuthenticationRequest;
import etfbl.ip.internshipserviceip.entities.Company;
import etfbl.ip.internshipserviceip.entities.Faculty;
import etfbl.ip.internshipserviceip.entities.Student;
import etfbl.ip.internshipserviceip.services.AuthenticationService;
import etfbl.ip.internshipserviceip.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private FacultyService facultyService;


    @PostMapping("/faculty")
    public ResponseEntity<Faculty> authenticateFaculty(@RequestBody AuthenticationRequest authenticationRequest) {
        Faculty faculty = authenticationService.authenticateFaculty(authenticationRequest);
        if(faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/company")
    public ResponseEntity<Company> authenticateCompany(@RequestBody AuthenticationRequest authenticationRequest) {
        Company company = authenticationService.authenticateCompany(authenticationRequest);
        if(company != null) {
            if(company.getActive())
              return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/student")
    public ResponseEntity<Student> authenticateStudent(@RequestBody AuthenticationRequest authenticationRequest) {
        Student student = authenticationService.authenticateStudent(authenticationRequest);
        if(student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/company/change_pass")
    ResponseEntity<Company> changePassword(@RequestBody AuthenticationRequest authenticationRequest) {
        Company company = authenticationService.changeCompanyPassword(authenticationRequest);
        if(company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }
}

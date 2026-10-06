package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final AuthenticationService authenticationService;

    public FacultyService(FacultyRepository facultyRepository, AuthenticationService authenticationService) {
        this.facultyRepository = facultyRepository;
        this.authenticationService = authenticationService;
    }

    public void setPassword(String email, String password) {
        var optional = facultyRepository.findByEmail(email);
        if (optional.isEmpty()) {
            return;
        }
        var faculty = optional.get();
        faculty.setPassword(authenticationService.hashPassword(password));
        facultyRepository.save(faculty);
    }
}

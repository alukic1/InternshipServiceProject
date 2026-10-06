package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.entities.ApplicationStatus;
import etfbl.ip.internshipserviceip.entities.InternshipApplication;
import etfbl.ip.internshipserviceip.entities.Student;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.repositories.InternshipApplicationRepository;
import etfbl.ip.internshipserviceip.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InternshipApplicationRepository internshipApplicationRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public List<Student> getAllStudentsByFaculty(Long facultyId) {
        return studentRepository.findAllByFacultyId(facultyId);
    }
    public Student getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found - id: " + studentId));
        return student;
    }
    public Student addStudent(Student student) {
        student.setPassword(authenticationService.hashPassword(student.getPassword()));
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student studentById = studentRepository.findById(student.getId()).get();
        studentById.setFullname(student.getFullname());
        studentById.setEmail(student.getEmail());
        studentById.setPassword(authenticationService.hashPassword(student.getPassword()));
        return studentRepository.save(studentById);
    }
    public void deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found - id: " + studentId));
        studentRepository.delete(student);
    }

    public List<Student> getAcceptedStudentsByInternship(Long internshipId) {
        List<InternshipApplication> applications = internshipApplicationRepository.findAllByInternshipIdAndStatus(internshipId, ApplicationStatus.ACCEPTED);
        List<Student> acceptedStudents = new ArrayList<>();
        for (InternshipApplication application : applications) {
                acceptedStudents.add(application.getStudent());

        }
        return acceptedStudents;
    }

    public List<Student> addStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}

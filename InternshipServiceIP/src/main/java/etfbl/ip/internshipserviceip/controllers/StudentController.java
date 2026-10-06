package etfbl.ip.internshipserviceip.controllers;

import etfbl.ip.internshipserviceip.dtos.PostCv;
import etfbl.ip.internshipserviceip.dtos.PostDiary;
import etfbl.ip.internshipserviceip.entities.Cv;
import etfbl.ip.internshipserviceip.entities.Diary;
import etfbl.ip.internshipserviceip.entities.Review;
import etfbl.ip.internshipserviceip.entities.Student;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.services.CvService;
import etfbl.ip.internshipserviceip.services.DiaryService;
import etfbl.ip.internshipserviceip.services.ReviewService;
import etfbl.ip.internshipserviceip.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CvService cvService;
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private ReviewService reviewService;


    @GetMapping
    public ResponseEntity<List<Student>> getStudentsByFacultyId(@RequestParam Long facultyId) {
        List<Student> students = studentService.getAllStudentsByFaculty(facultyId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<Cv> getCvByStudentId(@PathVariable Long id){
        Cv cv = cvService.getCvByStudentId(id);
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> getCvPicture(@PathVariable Long id) {

       Cv cv = cvService.getCvByStudentId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(cv.getPicture());
    }

    @GetMapping("{id}/cv/pdf")
    public ResponseEntity<byte[]> downloadCv(@PathVariable Long id) {

        byte[] pdf = cvService.getCvPdfByStudentId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=cv.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


    @PostMapping("/{id}/cv")
    public ResponseEntity<?> uploadCv(
            @PathVariable Long id,
            @RequestParam("cv") String cvJson,
            @RequestParam("picture") MultipartFile picture) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Cv cv = mapper.readValue(cvJson, Cv.class);
        if(picture.getContentType().startsWith("image/"))
            cv.setPicture(picture.getBytes());
        Student student = studentService.getStudentById(id);
        cv.setStudent(student);
        cvService.addCv(cv);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cv")
    public ResponseEntity<Cv> updateCv(@RequestParam("cv") String cvJson,
                                       @RequestParam("picture") MultipartFile picture) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Cv cv = mapper.readValue(cvJson, Cv.class);
        cv.setPicture(picture.getBytes());
        Cv updated = cvService.updateCv(cv);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/internship/{id}/accepted")
    public ResponseEntity<List<Student>> getAcceptedStudents(@PathVariable Long id){
        List<Student> students = studentService.getAcceptedStudentsByInternship(id);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/diary")
    public ResponseEntity<List<Diary>> getStudentDiary(@PathVariable Long id){
        List<Diary> diary = diaryService.getDiaryByStudentId(id);
        return ResponseEntity.ok(diary);
    }
    @GetMapping("/{id}/diary/internship/{internshipId}")
    public ResponseEntity<List<Diary>> getStudentDiaryByInternshipId(@PathVariable Long id, @PathVariable Long internshipId){
        List<Diary> diaries = diaryService.getDiaryByStudentIdAndInternshipId(id, internshipId);
        return ResponseEntity.ok(diaries);
    }

    @PostMapping("/diary")
    public ResponseEntity<Diary> addStudentDiary(@RequestBody PostDiary diary) {
        Diary newDiary = new Diary(diary);
        Diary added = diaryService.addDiary(newDiary);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }

    @PutMapping("/diary/{id}")
    public ResponseEntity<Diary> updateStudentDiary(@PathVariable Long id, @RequestBody PostDiary diary) {
        Diary updated = diaryService.updateDiary(id, diary);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/diary/{id}")
    public ResponseEntity deleteStudentDiary(@PathVariable Long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add_students")
    public ResponseEntity<List<Student>> uploadStudentCsv(@RequestBody List<Student> students) {
        List<Student> list = studentService.addStudents(students);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> addStudentReview(@RequestBody Review review) {
        review.setCreated(LocalDateTime.now());
        reviewService.addReview(review);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/company_review")
    public ResponseEntity<List<Review>> getCompanyReviewForStudent(@PathVariable Long id){
        List<Review> reviews = reviewService.getCompanyReviewsByStudentId(id);
        return ResponseEntity.ok(reviews);
    }
}

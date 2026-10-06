package ip.is.facultyapp.beans;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import ip.is.facultyapp.dao.StudentDAO;
import ip.is.facultyapp.dto.Diary;
import ip.is.facultyapp.dto.Review;
import ip.is.facultyapp.dto.Student;

public class StudentBean implements Serializable {

	private static final long serialVersionUID = 743390273730538482L;

	public List<Student> getStudents(Long id){
		return StudentDAO.findAll(id);
	}
	
	public Student getStudentById(Long id) {
		return StudentDAO.findById(id);
	}
	
	public void addStudent(Student student) {
		 StudentDAO.addStudent(student);
	}
	
	public void updateStudent(Student student) {
		StudentDAO.updateStudent(student);
	}
	
	public void deleteStudent(Long id) {
		StudentDAO.deleteStudent(id);
	}
	
	public void uploadStudentsCsv(File csv, Long id) {
		StudentDAO.uploadStudents(csv, id);
	}
	
	public List<Diary> getStudentDiaries(Long studentId){
		return StudentDAO.getStudentDiary(studentId);
	}
	
	public List<Review> getCompanyReviewsForStudent(Long studentId){
		return StudentDAO.getCompanyReviewsForStudent(studentId);
	}
	
	public void addReview(Review review) {
		StudentDAO.addReview(review);
	}
}

package ip.is.facultyapp.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ip.is.facultyapp.beans.CompanyBean;
import ip.is.facultyapp.beans.FacultyBean;
import ip.is.facultyapp.beans.InternshipBean;
import ip.is.facultyapp.beans.StudentBean;
import ip.is.facultyapp.dto.AuthenticationRequest;
import ip.is.facultyapp.dto.Company;
import ip.is.facultyapp.dto.Faculty;
import ip.is.facultyapp.dto.Internship;
import ip.is.facultyapp.dto.Review;
import ip.is.facultyapp.dto.ReviewerType;
import ip.is.facultyapp.dto.Student;

@MultipartConfig
@WebServlet("/Controller")
public class Controller extends HttpServlet{

	private static final long serialVersionUID = -2557588496562664321L;

	public Controller() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		session.setAttribute("notification", "");
		
		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		
		}else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		} 
		else if(action.equals("login")) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			    
			AuthenticationRequest req = new AuthenticationRequest(email, password);
			FacultyBean bean = (FacultyBean) session.getAttribute("facultyBean");
			if(bean == null) bean = new FacultyBean();
			
			Faculty faculty = bean.login(req);
			
			if(faculty != null){
				System.out.println(faculty);
		        session.setAttribute("faculty", faculty);
		        session.setAttribute("facultyBean", bean);
		        address = "/WEB-INF/pages/companies.jsp";
		    } else {
		        request.setAttribute("error", "Wrong email or password");
		        address = "/WEB-INF/pages/login.jsp";
		    }

        }
        else if(action.equals("companies")) {
            CompanyBean companyBean = (CompanyBean) session.getAttribute("companyBean");
            if(companyBean == null) companyBean = new CompanyBean();
            session.setAttribute("companyBean", companyBean);
            request.setAttribute("companies", companyBean.getCompanies());
            address = "/WEB-INF/pages/companies.jsp";
        }
        else if(action.equals("internships")) {
        	InternshipBean bean = (InternshipBean) session.getAttribute("internshipBean");
        	if(bean == null) bean = new InternshipBean();
        	session.setAttribute("internshipBean", bean);
        	request.setAttribute("internships", bean.getInternships());
        	address = "/WEB-INF/pages/internships.jsp";
        	
        }
        else if(action.equals("students")) {
            StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
            if(studentBean == null) studentBean = new StudentBean();
            session.setAttribute("studentBean", studentBean);
            Faculty faculty = (Faculty)session.getAttribute("faculty");
            if(faculty != null) {
            request.setAttribute("students", studentBean.getStudents(faculty.getId()));
            address = "/WEB-INF/pages/students.jsp"; }
            else
            	address="/WEB-INF-pages/login.jsp";
        }
        else if(action.equals("uploadCSV")) {
            Part filePart = request.getPart("csvFile"); 
            File file = new File(System.getProperty("java.io.tmpdir") + "/" + filePart.getSubmittedFileName());
            filePart.write(file.getAbsolutePath());
            Faculty faculty = (Faculty)session.getAttribute("faculty");
            if(faculty != null) {
            StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
            if(studentBean == null) studentBean = new StudentBean();
            studentBean.uploadStudentsCsv(file, faculty.getId());
            session.setAttribute("studentBean", studentBean);
           
            request.setAttribute("students", studentBean.getStudents(faculty.getId()));
            address = "/WEB-INF/pages/students.jsp";
            }
            else {
            	address="/WEB-INF-pages/login.jsp";
            }
        }
        else if(action.equals("toggleCompany")) {
            String idParam = request.getParameter("companyId");
            if(idParam != null) {
                Long companyId = Long.parseLong(idParam);
                CompanyBean companyBean = (CompanyBean) session.getAttribute("companyBean");
                if(companyBean == null) companyBean = new CompanyBean();

                companyBean.toggleActivation(companyId);
                
                session.setAttribute("companyBean", companyBean);
            }
            address = "/WEB-INF/pages/companies.jsp";
        }
        else if(action.equals("addCompany")){
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            CompanyBean bean = (CompanyBean) session.getAttribute("companyBean");
            if(bean == null) bean = new CompanyBean();

            bean.addCompany(new Company(null, name, email, password, false));
            session.setAttribute("companyBean", bean);

            address = "/WEB-INF/pages/companies.jsp";
        }
        else if(action.equals("deleteCompany")){
            Long id = Long.parseLong(request.getParameter("companyId"));

            CompanyBean bean = (CompanyBean) session.getAttribute("companyBean");
            bean.deleteCompany(id);

            address = "/WEB-INF/pages/companies.jsp";
        }
        else if(action.equals("addStudent")){
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            StudentBean bean = (StudentBean) session.getAttribute("studentBean");
            if(bean == null) bean = new StudentBean();
            
            Faculty f = (Faculty)session.getAttribute("faculty");
            if(f != null) {        
            bean.addStudent(new Student(null, fullname, email, password, f));

            address = "/WEB-INF/pages/students.jsp";
            }
            else {
            	address = "/WEB-INF/pages/login.jsp";
            }
        }
        else if(action.equals("editStudent")){
            Long id = Long.parseLong(request.getParameter("studentId"));

            StudentBean bean = (StudentBean) session.getAttribute("studentBean");
            if(bean == null) bean = new StudentBean();
            Student s = bean.getStudentById(id);

            request.setAttribute("editStudent", s);
            address = "/WEB-INF/pages/students.jsp";
        }
        else if(action.equals("updateStudent")){
            Long id = Long.parseLong(request.getParameter("studentId"));
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            StudentBean bean = (StudentBean) session.getAttribute("studentBean");
            if(bean == null) bean = new StudentBean();
            Faculty f = (Faculty)session.getAttribute("faculty");
            if(f != null) { 
            bean.updateStudent(new Student(id, fullname, email, password, f));

            address = "/WEB-INF/pages/students.jsp"; }
            else {
            	address = "/WEB-INF/pages/login.jsp";
            }
        }
        else if(action.equals("deleteStudent")){
            Long id = Long.parseLong(request.getParameter("studentId"));

            StudentBean bean = (StudentBean) session.getAttribute("studentBean");
            if(bean == null) bean = new StudentBean();
            bean.deleteStudent(id);

            address = "/WEB-INF/pages/students.jsp";
        }
        else if(action.equals("tracking")){
        	Long studentId;
        	 StudentBean studentBean1 = (StudentBean) session.getAttribute("studentBean");
             if(studentBean1 == null) studentBean1 = new StudentBean();
        	if(request.getParameter("studentId") != null) {
        		 studentId = Long.parseLong(request.getParameter("studentId")); 
                Student s = studentBean1.getStudentById(studentId);
                request.setAttribute("selectedStudent", s);
        	}
 
            address = "/WEB-INF/pages/reviews.jsp";
        }
        else if(action.equals("addGrade")){
            Long studentId = Long.parseLong(request.getParameter("studentId"));
            Long internshipId = Long.parseLong(request.getParameter("internshipId"));
            
            int grade = Integer.parseInt(request.getParameter("grade"));
            String comment = request.getParameter("comment");

            StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
            if(studentBean == null) studentBean = new StudentBean();
            Student s = studentBean.getStudentById(studentId);
            
            Review r = new Review();
            r.setStudent(s);
            r.setGrade(grade);
            r.setComment(comment);
            Faculty f = (Faculty)session.getAttribute("faculty");
            if(f!=null)
            	r.setReviewerId(f.getId());
            r.setReviewerType(ReviewerType.FACULTY);
            Internship i = new Internship(internshipId);
            r.setInternship(i);
            studentBean.addReview(r);
            request.setAttribute("selectedStudent", s);
            address = "/WEB-INF/pages/reviews.jsp";
            
        }
		
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher(address);
	     dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

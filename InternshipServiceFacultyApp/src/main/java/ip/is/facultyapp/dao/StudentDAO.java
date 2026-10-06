package ip.is.facultyapp.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ip.is.facultyapp.dto.Company;
import ip.is.facultyapp.dto.Diary;
import ip.is.facultyapp.dto.Faculty;
import ip.is.facultyapp.dto.Internship;
import ip.is.facultyapp.dto.Review;
import ip.is.facultyapp.dto.Student;

public class StudentDAO {

	private final static String API_URL = "http://localhost:8080/api/students";
	
	public static List<Student> findAll(Long facultyId){
		try {
            URL url = new URL(API_URL + "?facultyId=" + facultyId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            conn.disconnect();

            Gson gson = new Gson();
            List<Student> students = gson.fromJson(sb.toString(), new TypeToken<List<Student>>(){}.getType());
            return students;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static Student findById(Long id) {
		try {
			URL url = new URL(API_URL + "/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            conn.disconnect();

            Gson gson = new Gson();
            Student student = gson.fromJson(sb.toString(), new TypeToken<Student>(){}.getType());
            return student;
		}catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void addStudent(Student student) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(student);
			URL url = new URL(API_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
    		conn.setDoOutput(true);
    		
    		conn.getOutputStream().write(json.getBytes());
    		conn.getOutputStream().flush();
    		conn.getOutputStream().close();
    		
    		if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
    			throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
    		
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void updateStudent(Student student) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(student);
			URL url = new URL(API_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
    		conn.setDoOutput(true);
    		
    		conn.getOutputStream().write(json.getBytes());
    		conn.getOutputStream().flush();
    		conn.getOutputStream().close();
    		
    		if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("HTTP PUT Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteStudent(Long id) {
		try {
			URL url = new URL(API_URL + "/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("HTTP DELETE Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void uploadStudents(File csvFile, Long id) {
		List<Student> students = addStudentsCsv(csvFile, id);
		addStudentList(students);
	}
	
	private static void addStudentList(List<Student> list) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			System.out.println(json);
			URL url = new URL(API_URL + "/add_students");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
    		conn.setDoOutput(true);
    		
    		conn.getOutputStream().write(json.getBytes());
    		conn.getOutputStream().flush();
    		conn.getOutputStream().close();
    		
    		if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
    		
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	
	private static List<Student> addStudentsCsv(File csvFile, Long currentFaculty) {
		List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] tokens = line.split(",");
                if(tokens.length < 3) continue; //fullname, email, password

                String fullname = tokens[0].trim();
                String email = tokens[1].trim();
                String password = tokens[2].trim();
 
                Faculty f = new Faculty(currentFaculty);
                Student s = new Student(null, fullname, email, password, f);
                students.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
	}
	
	public static List<Diary> getStudentDiary(Long studentId){
		try {
            URL url = new URL(API_URL + "/" + studentId + "/diary");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            conn.disconnect();

            Gson gson = new Gson();
            List<Diary> diaries = gson.fromJson(sb.toString(), new TypeToken<List<Diary>>(){}.getType());
            return diaries;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static List<Review> getCompanyReviewsForStudent(Long studentId){
		try {
            URL url = new URL(API_URL + "/" + studentId + "/company_review");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            conn.disconnect();

            Gson gson = new Gson();
            List<Review> reviews = gson.fromJson(sb.toString(), new TypeToken<List<Review>>(){}.getType());
            return reviews;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void addReview(Review review) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(review);
			URL url = new URL(API_URL + "/review");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
    		conn.setDoOutput(true);
    		
    		conn.getOutputStream().write(json.getBytes());
    		conn.getOutputStream().flush();
    		conn.getOutputStream().close();
    		
    		if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
    			throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
    		
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
}

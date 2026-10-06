package ip.is.facultyapp.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import ip.is.facultyapp.dto.AuthenticationRequest;
import ip.is.facultyapp.dto.Faculty;

public class FacultyDAO {

	private static final String API = "http://localhost:8080/api/auth/faculty";
	
	public static Faculty login(AuthenticationRequest request) {
		try {
	        Gson gson = new Gson();

	        URL url = new URL(API);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);

	        String json = gson.toJson(request);
	        conn.getOutputStream().write(json.getBytes());

	        int status = conn.getResponseCode();
	        System.out.println("STATUS: " + status);
	        if(status == HttpURLConnection.HTTP_OK){
	            BufferedReader br = new BufferedReader(
	                new InputStreamReader(conn.getInputStream())
	            );
	            return gson.fromJson(br, Faculty.class);
	        }

	    } catch(Exception e){
	        e.printStackTrace();
	    }
	    return null;
	}
}

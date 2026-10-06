package ip.is.facultyapp.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ip.is.facultyapp.dto.Company;
import ip.is.facultyapp.dto.Internship;

public class InternshipDAO {

	private final static String API_URL = "http://localhost:8080/api/internships";
	
	public static List<Internship> findAll(){
		try {
            URL url = new URL(API_URL);
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
            List<Internship> internships = gson.fromJson(sb.toString(), new TypeToken<List<Internship>>(){}.getType());
            System.out.println(internships);
            return internships;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}

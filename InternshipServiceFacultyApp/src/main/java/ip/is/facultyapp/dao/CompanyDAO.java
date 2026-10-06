package ip.is.facultyapp.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ip.is.facultyapp.dto.Company;

public class CompanyDAO {

	private final static String API_URL = "http://localhost:8080/api/companies";
	
	public static List<Company> findAll(){
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
            List<Company> companies = gson.fromJson(sb.toString(), new TypeToken<List<Company>>(){}.getType());
            return companies;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static Company findById(Long id) {
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
            Company company = gson.fromJson(sb.toString(), new TypeToken<Company>(){}.getType());
            return company;
		}catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void addCompany(Company company) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(company);
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
	
	public static void updateCompany(Company company) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(company);
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
	
	public static void deleteCompany(Long id) {
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
	
	public static void toggleActivation(Long id) {
		try {
			URL url = new URL(API_URL + "/" + id + "/toggle_activation");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("HTTP PUT Request Failed with Error code : "
                        + conn.getResponseCode());
    		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

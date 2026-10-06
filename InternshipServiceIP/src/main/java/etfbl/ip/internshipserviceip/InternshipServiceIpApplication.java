package etfbl.ip.internshipserviceip;

import etfbl.ip.internshipserviceip.services.AiRecommendationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InternshipServiceIpApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipServiceIpApplication.class, args);
    }

}

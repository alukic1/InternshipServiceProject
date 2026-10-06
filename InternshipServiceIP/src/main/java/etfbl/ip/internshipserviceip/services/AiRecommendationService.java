package etfbl.ip.internshipserviceip.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import etfbl.ip.internshipserviceip.dtos.ComparedInternship;
import etfbl.ip.internshipserviceip.entities.Cv;
import etfbl.ip.internshipserviceip.entities.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AiRecommendationService {

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private CvService cvService;

    @Value("${gemini.api.key}")
    private String apiKey;

    public List<ComparedInternship> getRecommendation(Long studentId) {
        Client client = Client.builder().apiKey(apiKey).build();
        Cv cv = cvService.getCvByStudentId(studentId);
        String prompt = "You are an internship recommendation service. I need you to review this CV: " + cv.toString()
                + "and a list of internships. Then, give me a suitability score between 0 and 1 for each of the internships based on the given CV and short explanation for the result."
                + "The answer needs to be in JSON format with attributes internshipId, suitabilityScore, explanation. "
                + getAllInternshipsString();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        prompt,
                        null);

        String text = response.text();
        String json = text.substring(text.indexOf("["), text.lastIndexOf("]") + 1);

        ObjectMapper mapper = new ObjectMapper();
        List<ComparedInternship> list = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, ComparedInternship.class));
        list = list.stream().sorted(Comparator.comparingDouble(ComparedInternship::getSuitabilityScore).reversed()).limit(5).collect(Collectors.toList());
        expandDetails(list);
        return list;

    }

    private String getAllInternshipsString(){
        List<Internship> internshipList = internshipService.getAllInternships();
        StringBuilder sb = new StringBuilder("Available internships:\n");
        for(Internship internship : internshipList){
            sb.append(internship.toString());
        }
        return sb.toString();
    }

    private List<ComparedInternship> expandDetails (List<ComparedInternship> list){
        List<Internship> allInternships = internshipService.getAllInternships();
        Map<Long, Internship> internshipMap = allInternships.stream()
                .collect(Collectors.toMap(Internship::getId, i -> i));

        for (ComparedInternship ci : list) {
            ci.setInternshipData(internshipMap.get(ci.getInternshipId()));
        }
        return list;
    }
}

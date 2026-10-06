package etfbl.ip.internshipserviceip.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCv {
    private Long id;
    private Long studentId;
    private String summary;
    private String education;
    private String experience;
    private String skills;
    private String languages;
    private String interests;
    private String projects;
    private String pictureBase64;
}

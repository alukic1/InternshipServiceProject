package etfbl.ip.internshipserviceip.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiary {
    private Long studentId;
    private Long internshipId;
    private Integer weekNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}

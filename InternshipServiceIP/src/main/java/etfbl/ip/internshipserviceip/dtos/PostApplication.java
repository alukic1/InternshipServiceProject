package etfbl.ip.internshipserviceip.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostApplication {
    Long internshipId;
    Long studentId;
}

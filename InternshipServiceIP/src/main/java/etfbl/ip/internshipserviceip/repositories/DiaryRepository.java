package etfbl.ip.internshipserviceip.repositories;

import etfbl.ip.internshipserviceip.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findDiaryByStudentId(long studentId);
    List<Diary> findDiaryByStudentIdAndInternshipId(long studentId, long internshipId);
}

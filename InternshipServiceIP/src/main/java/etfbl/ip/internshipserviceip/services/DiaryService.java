package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.dtos.PostDiary;
import etfbl.ip.internshipserviceip.entities.Diary;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.repositories.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public List<Diary> getDiaryByStudentId(Long studentId) {
        return diaryRepository.findDiaryByStudentId(studentId);
    }

    public Diary addDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    public List<Diary> getDiaryByStudentIdAndInternshipId(Long studentId, Long internshipId) {
        return diaryRepository.findDiaryByStudentIdAndInternshipId(studentId, internshipId);
    }

    public Diary updateDiary(Long id, PostDiary diary){
        Diary diaryById = diaryRepository.findById(id).get();
        diaryById.setDescription(diary.getDescription());
        diaryById.setWeekNumber(diary.getWeekNumber());
        diaryById.setStartDate(diary.getStartDate());
        diaryById.setEndDate(diary.getEndDate());
        return diaryRepository.save(diaryById);
    }

    public void deleteDiary(Long id) {
       Diary diaryById = diaryRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Diary not found - id: " + id));

       diaryRepository.delete(diaryById);
    }
}

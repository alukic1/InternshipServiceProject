package etfbl.ip.internshipserviceip.services;

import etfbl.ip.internshipserviceip.entities.Cv;
import etfbl.ip.internshipserviceip.entities.Student;
import etfbl.ip.internshipserviceip.exceptions.ResourceNotFoundException;
import etfbl.ip.internshipserviceip.repositories.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CvService {

    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private StudentService studentService;

    public Cv getCvByStudentId(Long studentId){

        return cvRepository.findByStudentId(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("CV not found for student - id: " + studentId));
    }

    public Cv addCv(Cv cv){
        return cvRepository.save(cv);
    }

    public Cv updateCv(Cv cv){
        Cv cvById = cvRepository.findById(cv.getId()).get();
        cvById.setEducation( cv.getEducation());
        cvById.setExperience( cv.getExperience());
        cvById.setProjects( cv.getProjects());
        cvById.setLanguages( cv.getLanguages());
        cvById.setSummary( cv.getSummary());
        cvById.setSkills( cv.getSkills());
        cvById.setInterests( cv.getInterests());
        cvById.setPicture( cv.getPicture());
        return cvRepository.save(cvById);
    }

    public byte[] getCvPdfByStudentId(Long studentId){
        try{
            Student student = studentService.getStudentById(studentId);
            Cv cv = getCvByStudentId(studentId);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("CV"));
            document.add(new Paragraph(" "));

            if (cv.getPicture() != null) {
                Image img = Image.getInstance(cv.getPicture());
                img.scaleToFit(150, 150);
                document.add(img);
            }
            document.add(new Paragraph("Name: " + student.getFullname()));
            document.add(new Paragraph("Email: " + student.getEmail()));
            document.add(new Paragraph("Faculty: " + student.getFaculty().getName()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("About me: "));
            document.add(new Paragraph(cv.getSummary()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Education:"));
            document.add(new Paragraph(cv.getEducation()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Experience:"));
            document.add(new Paragraph(cv.getExperience()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Projects:"));
            document.add(new Paragraph(cv.getProjects()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Interests:"));
            document.add(new Paragraph(cv.getInterests()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Languages:"));
            document.add(new Paragraph(cv.getLanguages()));
            document.add(new Paragraph(" "));

            document.close();

            return outputStream.toByteArray();
        }
        catch(Exception e){
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}

package com.example.eppms.services;

import com.example.eppms.models.Exampaper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class ExampaperPdfExportService {

    private static ExampaperPdfExportService instance;

    private ExampaperPdfExportService() {}

    public static ExampaperPdfExportService getInstance() {
        if (instance == null) {
            synchronized (ExampaperPdfExportService.class) {
                if (instance == null) {
                    instance = new ExampaperPdfExportService();
                }
            }
        }
        return instance;
    }

    public void export(HttpServletResponse response, Exampaper exampaper) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                stream.beginText();

                // Başlık
                stream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                stream.newLineAtOffset(50, 750);
                stream.showText("Exam Paper Details");
                stream.newLineAtOffset(0, -30);

                // İçerik fontu ve satır yüksekliği
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.setLeading(20f);

                // Detaylar
                stream.showText("ID: " + safeString(exampaper.getId()));
                stream.newLine();
                stream.showText("Time: " + safeString(exampaper.getExamPaperTime()));
                stream.newLine();
                stream.showText("Version: " + safeString(exampaper.getExamVersion()));
                stream.newLine();

                // Courses Exam bilgisi
                if (exampaper.getCourseExam() != null) {
                    stream.showText("Courses Exam: " + safeString(exampaper.getCourseExam().getCourseExamTitle()));
                    stream.newLine();
                }

                // Lecturer bilgisi (First + Last Name)
                if (exampaper.getLecturer() != null) {
                    String fullName = safeString(exampaper.getLecturer().getLecturerFirstName()) + " " + safeString(exampaper.getLecturer().getLecturerLastName());
                    stream.showText("Lecturer: " + fullName);
                    stream.newLine();
                }

                stream.endText();
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=exampaper-" + exampaper.getId() + ".pdf");

            document.save(response.getOutputStream());
            response.getOutputStream().flush();
        }
    }

    // Null güvenli string dönüşümü
    private String safeString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}

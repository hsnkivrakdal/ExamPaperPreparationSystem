package com.example.eppms.services;

import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Coursesexam;
import com.example.eppms.models.Cours;
import com.example.eppms.models.Lecturer;
import com.example.eppms.models.Examquestion;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExampaperPdfExportService {

    // Singleton instance
    private static volatile ExampaperPdfExportService instance;
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
            PDPageContentStream stream = new PDPageContentStream(document, page);
            float y = 800;
            float left = 50;
            float width = 500;
            PDColor black = new PDColor(new float[]{0, 0, 0}, PDDeviceRGB.INSTANCE);
            PDColor gray = new PDColor(new float[]{0.95f, 0.95f, 0.95f}, PDDeviceRGB.INSTANCE);
            PDType0Font font = PDType0Font.load(document, new java.io.File("C:/Windows/Fonts/arial.ttf"));

            // --- LOGO ve ÜST BAŞLIK ---
            try {
                PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/static/img/logo.png", document);
                stream.drawImage(logo, left, y - 10, 50, 50);
            } catch (Exception e) { /* logo yoksa hata verme */ }
            stream.setNonStrokingColor(black);
            stream.beginText();
            stream.setFont(font, 20);
            stream.newLineAtOffset(left + 65, y + 20);
            stream.showText("REPUBLIC OF TURKEY");
            stream.endText();
            stream.beginText();
            stream.setFont(font, 16);
            stream.newLineAtOffset(left + 65, y);
            stream.showText(getString(exampaper, "university"));
            stream.endText();
            stream.beginText();
            stream.setFont(font, 13);
            stream.newLineAtOffset(left + 65, y - 18);
            stream.showText(getString(exampaper, "faculty") + " / " + getString(exampaper, "department"));
            stream.endText();
            y -= 60;

            // --- SINAV BAŞLIĞI ---
            stream.setNonStrokingColor(black);
            stream.setLineWidth(1.2f);
            stream.addRect(left, y, width, 30);
            stream.stroke();
            stream.beginText();
            stream.setFont(font, 16);
            float titleWidth = font.getStringWidth("EXAM PAPER") / 1000 * 16;
            float titleX = left + (width - titleWidth) / 2;
            stream.newLineAtOffset(titleX, y + 8);
            stream.showText("EXAM PAPER");
            stream.endText();
            y -= 40;

            // --- SINAV BİLGİLERİ ---
            float infoY = y;
            float infoX = left;
            float infoW = 240;
            float infoH = 18;
            String[][] info = {
                {"Course:", getString(exampaper, "courseTitle")},
                {"Course Code:", getString(exampaper, "courseCode")},
                {"Exam Type:", getString(exampaper, "examType")},
                {"Exam Date:", getString(exampaper, "examDate")},
                {"Duration:", getString(exampaper, "examTime") + " min"},
                {"Version:", getString(exampaper, "examVersion")},
                {"Lecturer:", getString(exampaper, "lecturer")}
            };
            for (int i = 0; i < info.length; i++) {
                stream.setNonStrokingColor(gray);
                stream.addRect(infoX, infoY, infoW, infoH);
                stream.fill();
                stream.setNonStrokingColor(black);
                stream.setLineWidth(0.5f);
                stream.addRect(infoX, infoY, infoW, infoH);
                stream.stroke();
                stream.beginText();
                stream.setFont(font, 11);
                stream.newLineAtOffset(infoX + 5, infoY + 4);
                stream.showText(info[i][0] + " " + info[i][1]);
                stream.endText();
                if (i % 2 == 1) {
                    infoX = left;
                    infoY -= infoH;
                } else {
                    infoX = left + infoW + 10;
                }
            }
            y = infoY - 25;

            // --- STUDENT INFO ---
            stream.setNonStrokingColor(gray);
            stream.addRect(left, y, width, 18);
            stream.fill();
            stream.setNonStrokingColor(black);
            stream.setLineWidth(0.5f);
            stream.addRect(left, y, width, 18);
            stream.stroke();
            stream.beginText();
            stream.setFont(font, 11);
            stream.newLineAtOffset(left + 5, y + 4);
            stream.showText("Name Surname: .............................................   Student No: ....................   Signature: ....................");
            stream.endText();
            y -= 28;

            // --- QUESTIONS ---
            int qNum = 1;
            for (var epq : exampaper.getExampaperquestions()) {
                var q = epq.getExamQuestion();
                if (q == null) continue;
                stream.setNonStrokingColor(black);
                stream.beginText();
                stream.setFont(font, 12);
                stream.newLineAtOffset(left, y);
                stream.showText(qNum + ". " + q.getQuestionText() + " [" + (q.getQuestionPoint() != null ? q.getQuestionPoint() : "") + " pts]");
                stream.endText();
                y -= 15;
                char opt = 'A';
                for (var optObj : q.getQuestionoptions()) {
                    stream.beginText();
                    stream.setFont(font, 11);
                    stream.newLineAtOffset(left + 18, y);
                    stream.showText(opt + ") " + (optObj.getOptionText() != null ? optObj.getOptionText() : ""));
                    stream.endText();
                    y -= 13;
                    opt++;
                }
                y -= 7;
                qNum++;
                if (y < 100) {
                    stream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    stream = new PDPageContentStream(document, page);
                    y = 800;
                }
            }

            // --- FOOTER NOTE ---
            y -= 8;
            stream.setNonStrokingColor(gray);
            stream.addRect(left, y, width, 16);
            stream.fill();
            stream.setNonStrokingColor(black);
            stream.setLineWidth(0.5f);
            stream.addRect(left, y, width, 16);
            stream.stroke();
            stream.beginText();
            stream.setFont(font, 10);
            stream.newLineAtOffset(left + 5, y + 3);
            stream.showText("Note: Cheating, impersonation, and similar acts are disciplinary offenses.");
            stream.endText();
            stream.close();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=exampaper-" + exampaper.getId() + ".pdf");
            document.save(response.getOutputStream());
            response.getOutputStream().flush();
        }
    }

    // Modelden string veri çekmek için yardımcı metotlar
    private String getString(Exampaper exampaper, String key) {
        try {
            Coursesexam courseExam = exampaper.getCourseExam();
            Cours course = courseExam != null ? courseExam.getCourse() : null;
            switch (key) {
                case "university":
                    if (course != null && course.getProgram() != null && course.getProgram().getDepartment() != null && course.getProgram().getDepartment().getFaculty() != null && course.getProgram().getDepartment().getFaculty().getUniversity() != null)
                        return course.getProgram().getDepartment().getFaculty().getUniversity().getName();
                    break;
                case "faculty":
                    if (course != null && course.getProgram() != null && course.getProgram().getDepartment() != null && course.getProgram().getDepartment().getFaculty() != null)
                        return course.getProgram().getDepartment().getFaculty().getFacultyName();
                    break;
                case "department":
                    if (course != null && course.getProgram() != null && course.getProgram().getDepartment() != null)
                        return course.getProgram().getDepartment().getDepartmentName();
                    break;
                case "courseTitle":
                    if (course != null) return course.getCourseTitle();
                    break;
                case "courseCode":
                    if (course != null) return course.getCourseCode();
                    break;
                case "examType":
                    if (courseExam != null && courseExam.getExamType() != null) return courseExam.getExamType().getExamTypeTitle();
                    break;
                case "examDate":
                    if (courseExam != null && courseExam.getCourseExamDate() != null) return courseExam.getCourseExamDate().toString();
                    break;
                case "examTime":
                    if (exampaper.getExamPaperTime() != null) return exampaper.getExamPaperTime().toString();
                    break;
                case "examVersion":
                    if (exampaper.getExamVersion() != null) return exampaper.getExamVersion();
                    break;
                case "lecturer":
                    if (exampaper.getLecturer() != null) return exampaper.getLecturer().getLecturerFirstName() + " " + exampaper.getLecturer().getLecturerLastName();
                    break;
            }
        } catch (Exception e) { return ""; }
        return "";
    }
}

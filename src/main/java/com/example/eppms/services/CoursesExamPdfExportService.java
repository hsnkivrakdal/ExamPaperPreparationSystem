package com.example.eppms.services;

import com.example.eppms.models.Coursesexam;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CoursesExamPdfExportService {

    private static CoursesExamPdfExportService instance;

    private CoursesExamPdfExportService() {}

    public static CoursesExamPdfExportService getInstance() {
        if (instance == null) {
            instance = new CoursesExamPdfExportService();
        }
        return instance;
    }

    // PDF dosyasını byte stream olarak dönen method
    public ByteArrayOutputStream exportToStream(Coursesexam exam) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(document, page);
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        stream.setLeading(20f);
        stream.newLineAtOffset(50, 750);

        stream.showText("Course Exam Details");
        stream.newLine();
        stream.newLine();
        stream.setFont(PDType1Font.HELVETICA, 12);

        stream.showText("ID: " + exam.getId());
        stream.newLine();
        stream.showText("Title: " + exam.getCourseExamTitle());
        stream.newLine();
        stream.showText("Date: " + exam.getCourseExamDate());
        stream.newLine();
        stream.showText("Capacity: " + exam.getCourseExamCapacity());
        stream.newLine();
        stream.showText("Section: " + exam.getCourseExamSection());
        stream.newLine();

        if (exam.getExamType() != null) {
            stream.showText("Exam Type: " + exam.getExamType().getExamTypeTitle());
            stream.newLine();
        }

        if (exam.getCourse() != null) {
            stream.showText("Course: " + exam.getCourse().getCourseTitle());
            stream.newLine();
        }

        stream.endText();
        stream.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream;
    }
}

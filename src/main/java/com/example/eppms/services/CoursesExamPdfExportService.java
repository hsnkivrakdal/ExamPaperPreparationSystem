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
        // Başlık ve üst kısım
        stream.setStrokingColor(0, 0, 0);
        stream.setLineWidth(1f);
        stream.addRect(40, 740, 515, 90); // Başlık kutusu
        stream.stroke();

        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        stream.newLineAtOffset(60, 800);
        stream.showText("AKADEMİK SINAV KAĞIDI");
        stream.endText();

        // Sınav bilgileri
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA, 12);
        stream.setLeading(18f);
        stream.newLineAtOffset(60, 770);
        stream.showText("Ders: " + (exam.getCourse() != null ? exam.getCourse().getCourseTitle() : "-"));
        stream.newLine();
        stream.showText("Sınav Başlığı: " + (exam.getCourseExamTitle() != null ? exam.getCourseExamTitle() : "-"));
        stream.newLine();
        stream.showText("Tarih: " + (exam.getCourseExamDate() != null ? exam.getCourseExamDate() : "-"));
        stream.newLine();
        stream.showText("Sınav Türü: " + (exam.getExamType() != null ? exam.getExamType().getExamTypeTitle() : "-"));
        stream.newLine();
        stream.showText("Bölüm: " + (exam.getCourseExamSection() != null ? exam.getCourseExamSection() : "-"));
        stream.endText();

        // Öğrenci Bilgileri Alanı
        stream.setLineWidth(0.5f);
        stream.addRect(40, 700, 515, 30);
        stream.stroke();
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA, 12);
        stream.newLineAtOffset(50, 715);
        stream.showText("Ad Soyad: .....................................................   No: ....................");
        stream.endText();

        // Sorular için örnek tablo başlığı
        stream.setLineWidth(0.5f);
        stream.addRect(40, 650, 515, 30);
        stream.stroke();
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA_BOLD, 13);
        stream.newLineAtOffset(50, 665);
        stream.showText("SORULAR");
        stream.endText();

        // Sorular (örnek, gerçek sorular eklenmek istenirse burada döngü ile eklenebilir)
        float y = 640;
        for (int i = 1; i <= 5; i++) {
            stream.setLineWidth(0.3f);
            stream.addRect(50, y, 495, 40);
            stream.stroke();
            stream.beginText();
            stream.setFont(PDType1Font.HELVETICA, 12);
            stream.newLineAtOffset(55, y + 25);
            stream.showText(i + ". Soru: ............................................................");
            stream.endText();
            y -= 50;
        }

        // Alt bilgi
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
        stream.newLineAtOffset(50, 100);
        stream.showText("Not: Cevaplarınızı tükenmez kalemle yazınız. Başarılar dileriz.");
        stream.endText();

        stream.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream;
    }
}

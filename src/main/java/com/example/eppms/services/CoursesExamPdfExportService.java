package com.example.eppms.services;

import com.example.eppms.models.Coursesexam;
import com.example.eppms.models.Exampaper;
import com.example.eppms.models.Exampaperquestion;
import com.example.eppms.models.Examquestion;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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

    // Uzun metinleri satırlara bölen yardımcı fonksiyon
    private List<String> wrapText(String text, int maxWidth, PDType1Font font, int fontSize) throws IOException {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            String testLine = line.length() == 0 ? word : line + " " + word;
            float size = font.getStringWidth(testLine) / 1000 * fontSize;
            if (size > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                if (line.length() > 0) line.append(" ");
                line.append(word);
            }
        }
        if (line.length() > 0) lines.add(line.toString());
        return lines;
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

        // Sorular (artık gerçek sorular Exampaper üzerinden alınacak)
        float y = 640;
        int maxWidth = 480; // kutu genişliği
        int fontSize = 12;
        float marginBottom = 100; // Alt boşluk
        // Exampaper'ı bul
        Exampaper exampaper = null;
        if (exam.getExampapers() != null && !exam.getExampapers().isEmpty()) {
            exampaper = exam.getExampapers().iterator().next();
        }
        if (exampaper != null && exampaper.getExampaperquestions() != null) {
            int soruNo = 1;
            for (Exampaperquestion epq : exampaper.getExampaperquestions()) {
                Examquestion question = epq.getExamQuestion();
                if (question == null) continue;
                String questionText = soruNo + ". Soru: " + question.getExamQuestionDefinition();
                List<String> lines = wrapText(questionText, maxWidth, PDType1Font.HELVETICA, fontSize);
                float boxHeight = 20 + lines.size() * 15;
                // Eğer kutu sayfaya sığmazsa yeni sayfa ekle
                if (y - boxHeight < marginBottom) {
                    stream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    stream = new PDPageContentStream(document, page);
                    y = 800;
                }
                stream.setLineWidth(0.3f);
                stream.addRect(50, y, 495, boxHeight);
                stream.stroke();
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, fontSize);
                stream.setLeading(15f);
                stream.newLineAtOffset(55, y + boxHeight - 15);
                for (String line : lines) {
                    stream.showText(line);
                    stream.newLine();
                }
                stream.endText();
                y -= (boxHeight + 10);
                soruNo++;
            }
        } else {
            // Soru yoksa örnek metin
            stream.beginText();
            stream.setFont(PDType1Font.HELVETICA, fontSize);
            stream.newLineAtOffset(55, y);
            stream.showText("Soru bulunamadı.");
            stream.endText();
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

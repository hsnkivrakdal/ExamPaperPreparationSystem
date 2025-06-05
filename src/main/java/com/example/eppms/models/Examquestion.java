package com.example.eppms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examquestions")
public class Examquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamQuestionId", nullable = false)
    private Integer id;

    @Column(name = "ExamQuestionDefinition", nullable = false, length = 550)
    private String examQuestionDefinition;

    @Column(name = "QuestionPoint")
    private Short questionPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QuestionTypeId")
    private Questiontype questionType;

    @OneToMany(mappedBy = "examQuestion")
    private Set<Exampaperquestion> exampaperquestions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "examQuestion")
    private Set<Questionoption> questionoptions = new LinkedHashSet<>();

    @Override
    public String toString() {
        return examQuestionDefinition;
    }

    public String getQuestionText() {
        return examQuestionDefinition;
    }

    // Şıklar için örnek metotlar (varsayım: questionoptions setinde A,B,C,D seçenekleri var)
    public String getOptionA() {
        return getOptionByLabel("A");
    }

    public String getOptionB() {
        return getOptionByLabel("B");
    }

    public String getOptionC() {
        return getOptionByLabel("C");
    }

    public String getOptionD() {
        return getOptionByLabel("D");
    }

    private String getOptionByLabel(String label) {
        if (questionoptions != null) {
            for (Questionoption option : questionoptions) {
                // OptionLabel alanı kaldırıldığı için, label kontrolü yapılamaz
                // Sadece sıralamaya göre döndürülür
                // A: 0, B: 1, C: 2, D: 3
                int idx = -1;
                switch (label.toUpperCase()) {
                    case "A":
                        idx = 0;
                        break;
                    case "B":
                        idx = 1;
                        break;
                    case "C":
                        idx = 2;
                        break;
                    case "D":
                        idx = 3;
                        break;
                }
                if (idx >= 0) {
                    int i = 0;
                    for (Questionoption opt : questionoptions) {
                        if (i == idx) {
                            return opt.getOptionText();
                        }
                        i++;
                    }
                }
                break;
            }
        }
        return null;
    }
}
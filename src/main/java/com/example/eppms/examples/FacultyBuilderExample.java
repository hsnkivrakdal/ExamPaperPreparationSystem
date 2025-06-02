package com.example.eppms.examples;

import com.example.eppms.builders.FacultyViewBuilder;
import com.example.eppms.builders.FacultyOperationBuilder;
import com.example.eppms.builders.FacultyOperationResult;
import com.example.eppms.builders.FacultyRedirectBuilder;
import com.example.eppms.models.Faculty;
import com.example.eppms.models.University;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * Bu sınıf FacultyController'da Builder Pattern kullanımının örneklerini gösterir.
 * Gerçek production kodunda bu sınıf kullanılmaz, sadece örnek amaçlıdır.
 */
public class FacultyBuilderExample {

    /**
     * Örnek 1: FacultyViewBuilder kullanımı
     */
    public String exampleViewBuilder(Model model, List<Faculty> faculties) {
        return FacultyViewBuilder.create()
                .withModel(model)
                .withFaculties(faculties)
                .withPageTitle("Faculty List Example")
                .withSuccessMessage("Data loaded successfully")
                .buildListView();
    }

    /**
     * Örnek 2: FacultyOperationBuilder kullanımı
     */
    public FacultyOperationResult exampleOperationBuilder(Faculty faculty) {
        // Not: Gerçek kullanımda service'ler dependency injection ile gelir
        return FacultyOperationBuilder.create()
                // .withFacultyService(facultyService)     // Real service would be injected
                // .withUniversityService(universityService) // Real service would be injected
                .createFaculty(faculty);
    }

    /**
     * Örnek 3: FacultyRedirectBuilder kullanımı
     */
    public String exampleRedirectBuilder(RedirectAttributes redirectAttributes, 
                                       FacultyOperationResult result) {
        return FacultyRedirectBuilder.create()
                .withRedirectAttributes(redirectAttributes)
                .buildRedirectWithResult(result, "/faculties/list", "/faculties/create");
    }

    /**
     * Örnek 4: Kapsamlı CRUD işlem örneği
     */
    public String exampleCompleteCreateFlow(Model model, Faculty faculty, 
                                          RedirectAttributes redirectAttributes) {
        
        // 1. Form gösterimi için View Builder
        if (faculty == null) {
            List<University> universities = Arrays.asList(new University()); // Mock data
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withEmptyFaculty()
                    .withUniversities(universities)
                    .withPageTitle("Create New Faculty")
                    .withFormAction("/faculties/create")
                    .asCreateMode()
                    .buildCreateView();
        }

        // 2. Faculty oluşturma işlemi için Operation Builder
        FacultyOperationResult result = FacultyOperationBuilder.create()
                // Real services would be injected here
                .createFaculty(faculty);

        // 3. Sonuca göre redirect için Redirect Builder
        return FacultyRedirectBuilder.create()
                .withRedirectAttributes(redirectAttributes)
                .buildRedirectWithResult(result, "/faculties/list", "/faculties/create");
    }

    /**
     * Örnek 5: Error handling ile birlikte kullanım
     */
    public String exampleWithErrorHandling(Model model, Integer facultyId, 
                                         RedirectAttributes redirectAttributes) {
        try {
            // Faculty getirme işlemi
            FacultyOperationResult result = FacultyOperationBuilder.create()
                    // Real services would be injected
                    .getFacultyById(facultyId);

            if (result.isFailure()) {
                // Hata durumunda redirect
                return FacultyRedirectBuilder.create()
                        .withRedirectAttributes(redirectAttributes)
                        .withErrorMessage(result.getMessage())
                        .buildErrorRedirect("/faculties/list");
            }

            // Başarılı durumda view gösterimi
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withFaculty(result.getFaculty())
                    .withPageTitle("Faculty Details: " + result.getFaculty().getFacultyName())
                    .buildListView();

        } catch (Exception e) {
            // Exception durumunda error view
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withPageTitle("Error")
                    .withErrorMessage("An unexpected error occurred: " + e.getMessage())
                    .buildListView();
        }
    }

    /**
     * Örnek 6: Method chaining ile fluent interface
     */
    public String exampleFluentInterface(Model model, List<Faculty> faculties) {
        return FacultyViewBuilder
                .create()                              // Builder oluştur
                .withModel(model)                      // Model ekle
                .withFaculties(faculties)              // Faculty listesi ekle
                .withPageTitle("Advanced Faculty List") // Sayfa başlığı
                .withSuccessMessage(                   // Dinamik mesaj
                    "Loaded " + faculties.size() + " faculties successfully"
                )
                .buildListView();                      // View'i oluştur
    }

    /**
     * Builder Pattern'in Avantajları:
     * 
     * 1. **Okunabilirlik**: Kod çok daha okunabilir ve anlaşılır
     * 2. **Esneklik**: İsteğe bağlı parametreler kolayca eklenebilir
     * 3. **Type Safety**: Compile-time'da tip güvenliği
     * 4. **Immutability**: Builder ile oluşturulan objeler immutable olabilir
     * 5. **Validation**: Builder seviyesinde validation yapılabilir
     * 6. **Fluent Interface**: Method chaining ile akıcı kullanım
     * 7. **Maintenance**: Yeni parametreler kolayca eklenebilir
     * 8. **Testing**: Mock builder'lar kolayca oluşturulabilir
     */
} 
package com.example.eppms.controllers;

import com.example.eppms.builders.FacultyViewBuilder;
import com.example.eppms.builders.FacultyOperationBuilder;
import com.example.eppms.builders.FacultyOperationResult;
import com.example.eppms.builders.FacultyRedirectBuilder;
import com.example.eppms.models.Faculty;
import com.example.eppms.models.University;
import com.example.eppms.services.FacultyService;
import com.example.eppms.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private UniversityService universityService;

    /**
     * Display all faculties with enhanced view builder
     */
    @GetMapping("/list")
    public String getAll(Model model) {
        try {
            List<Faculty> faculties = facultyService.getAll();
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withFaculties(faculties)
                    .withPageTitle("Faculty Management")
                    .withSuccessMessage("Faculties loaded successfully (" + faculties.size() + " found)")
                    .buildListView();
                    
        } catch (Exception e) {
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withPageTitle("Faculty Management")
                    .withErrorMessage("Failed to load faculties: " + e.getMessage())
                    .buildListView();
        }
    }

    /**
     * Show create faculty form with all universities
     */
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        try {
            List<University> universities = universityService.getAll();
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withEmptyFaculty()
                    .withUniversities(universities)
                    .withPageTitle("Create New Faculty")
                    .withFormAction("/faculties/create")
                    .asCreateMode()
                    .buildCreateView();
                    
        } catch (Exception e) {
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withPageTitle("Create New Faculty")
                    .withErrorMessage("Failed to load universities: " + e.getMessage())
                    .buildCreateView();
        }
    }

    /**
     * Process faculty creation with comprehensive validation and error handling
     */
    @PostMapping("/create")
    public String create(@ModelAttribute Faculty faculty, RedirectAttributes redirectAttributes) {
        // Execute creation operation using builder
        FacultyOperationResult result = FacultyOperationBuilder.create()
                .withFacultyService(facultyService)
                .withUniversityService(universityService)
                .withRedirectAttributes(redirectAttributes)
                .createFaculty(faculty);
        
        // Build redirect response using builder
        return FacultyRedirectBuilder.create()
                .withRedirectAttributes(redirectAttributes)
                .buildRedirectWithResult(result, "/faculties/list", "/faculties/create");
    }

    /**
     * Show edit faculty form with pre-populated data
     */
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        // Get faculty using operation builder
        FacultyOperationResult facultyResult = FacultyOperationBuilder.create()
                .withFacultyService(facultyService)
                .getFacultyById(id);
        
        if (facultyResult.isFailure()) {
            return FacultyRedirectBuilder.create()
                    .withRedirectAttributes(redirectAttributes)
                    .withErrorMessage(facultyResult.getMessage())
                    .buildErrorRedirect("/faculties/list");
        }
        
        try {
            List<University> universities = universityService.getAll();
            Faculty faculty = facultyResult.getFaculty();
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withFaculty(faculty)
                    .withUniversities(universities)
                    .withPageTitle("Edit Faculty: " + faculty.getFacultyName())
                    .withFormAction("/faculties/update")
                    .asEditMode()
                    .buildEditView();
                    
        } catch (Exception e) {
            return FacultyRedirectBuilder.create()
                    .withRedirectAttributes(redirectAttributes)
                    .withErrorMessage("Failed to load edit page: " + e.getMessage())
                    .buildErrorRedirect("/faculties/list");
        }
    }

    /**
     * Process faculty update with validation
     */
    @PostMapping("/update")
    public String update(@ModelAttribute Faculty faculty, RedirectAttributes redirectAttributes) {
        // Execute update operation using builder
        FacultyOperationResult result = FacultyOperationBuilder.create()
                .withFacultyService(facultyService)
                .withUniversityService(universityService)
                .withRedirectAttributes(redirectAttributes)
                .updateFaculty(faculty);
        
        // Build redirect response
        String successUrl = "/faculties/list";
        String errorUrl = "/faculties/edit/" + faculty.getId();
        
        return FacultyRedirectBuilder.create()
                .withRedirectAttributes(redirectAttributes)
                .buildRedirectWithResult(result, successUrl, errorUrl);
    }

    /**
     * Show delete confirmation page
     */
    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        // Get faculty using operation builder
        FacultyOperationResult facultyResult = FacultyOperationBuilder.create()
                .withFacultyService(facultyService)
                .getFacultyById(id);
        
        if (facultyResult.isFailure()) {
            return FacultyRedirectBuilder.create()
                    .withRedirectAttributes(redirectAttributes)
                    .withErrorMessage(facultyResult.getMessage())
                    .buildErrorRedirect("/faculties/list");
        }
        
        Faculty faculty = facultyResult.getFaculty();
        
        return FacultyViewBuilder.create()
                .withModel(model)
                .withFaculty(faculty)
                .withPageTitle("Delete Faculty: " + faculty.getFacultyName())
                .withWarningMessage("Are you sure you want to delete this faculty? This action cannot be undone.")
                .asDeleteMode()
                .buildDeleteView();
    }

    /**
     * Process faculty deletion with dependency checking
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // Execute deletion operation using builder
        FacultyOperationResult result = FacultyOperationBuilder.create()
                .withFacultyService(facultyService)
                .withUniversityService(universityService)
                .withRedirectAttributes(redirectAttributes)
                .deleteFaculty(id);
        
        // Build redirect response
        return FacultyRedirectBuilder.create()
                .withRedirectAttributes(redirectAttributes)
                .buildRedirectWithResult(result, "/faculties/list", "/faculties/list");
    }

    /**
     * Show faculties by university (additional feature using builders)
     */
    @GetMapping("/by-university/{universityId}")
    public String getFacultiesByUniversity(@PathVariable Integer universityId, Model model, RedirectAttributes redirectAttributes) {
        try {
            University university = universityService.getById(universityId);
            if (university == null) {
                return FacultyRedirectBuilder.create()
                        .withRedirectAttributes(redirectAttributes)
                        .withErrorMessage("University not found with ID: " + universityId)
                        .buildErrorRedirect("/faculties/list");
            }
            
            List<Faculty> allFaculties = facultyService.getAll();
            List<Faculty> universitiesFaculties = allFaculties.stream()
                    .filter(f -> f.getUniversity().getId().equals(universityId))
                    .collect(java.util.stream.Collectors.toList());
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withFaculties(universitiesFaculties)
                    .withPageTitle("Faculties of " + university.getName())
                    .withSuccessMessage("Found " + universitiesFaculties.size() + " faculties for " + university.getName())
                    .buildListView();
                    
        } catch (Exception e) {
            return FacultyRedirectBuilder.create()
                    .withRedirectAttributes(redirectAttributes)
                    .withErrorMessage("Failed to load faculties by university: " + e.getMessage())
                    .buildErrorRedirect("/faculties/list");
        }
    }

    /**
     * Search faculties (additional feature using builders)
     */
    @GetMapping("/search")
    public String searchFaculties(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer universityId,
            Model model) {
        try {
            List<Faculty> allFaculties = facultyService.getAll();
            List<Faculty> searchResults = allFaculties.stream()
                    .filter(faculty -> {
                        boolean matches = true;
                        
                        if (name != null && !name.trim().isEmpty()) {
                            matches = faculty.getFacultyName().toLowerCase()
                                    .contains(name.toLowerCase());
                        }
                        
                        if (matches && universityId != null) {
                            matches = faculty.getUniversity().getId().equals(universityId);
                        }
                        
                        return matches;
                    })
                    .collect(java.util.stream.Collectors.toList());
            
            String searchInfo = buildSearchInfo(name, universityId);
            
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withFaculties(searchResults)
                    .withPageTitle("Faculty Search Results")
                    .withSuccessMessage(searchInfo + " - Found " + searchResults.size() + " faculties")
                    .buildListView();
                    
        } catch (Exception e) {
            return FacultyViewBuilder.create()
                    .withModel(model)
                    .withPageTitle("Faculty Search Results")
                    .withErrorMessage("Search failed: " + e.getMessage())
                    .buildListView();
        }
    }

    /**
     * Helper method to build search information string
     */
    private String buildSearchInfo(String name, Integer universityId) {
        StringBuilder info = new StringBuilder("Search criteria: ");
        
        if (name != null && !name.trim().isEmpty()) {
            info.append("Name contains '").append(name).append("'");
        }
        
        if (universityId != null) {
            if (info.length() > 17) { // "Search criteria: ".length()
                info.append(", ");
            }
            try {
                University university = universityService.getById(universityId);
                if (university != null) {
                    info.append("University: ").append(university.getName());
                }
            } catch (Exception e) {
                info.append("University ID: ").append(universityId);
            }
        }
        
        if (info.length() == 17) { // Only "Search criteria: "
            info.append("All faculties");
        }
        
        return info.toString();
    }
}
package com.example.eppms.builders;

import com.example.eppms.models.Faculty;
import com.example.eppms.models.University;
import org.springframework.ui.Model;

import java.util.List;

public class FacultyViewBuilder {
    private Model model;
    private String pageTitle;
    private String successMessage;
    private String errorMessage;
    private String warningMessage;
    private Faculty faculty;
    private List<Faculty> faculties;
    private List<University> universities;
    private String formAction;
    private boolean isCreate = false;
    private boolean isEdit = false;
    private boolean isDelete = false;
    private int totalCount = 0;
    
    private FacultyViewBuilder() {}
    
    public static FacultyViewBuilder create() {
        return new FacultyViewBuilder();
    }
    
    public FacultyViewBuilder withModel(Model model) {
        this.model = model;
        return this;
    }
    
    public FacultyViewBuilder withPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }
    
    public FacultyViewBuilder withSuccessMessage(String message) {
        this.successMessage = message;
        return this;
    }
    
    public FacultyViewBuilder withErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }
    
    public FacultyViewBuilder withWarningMessage(String message) {
        this.warningMessage = message;
        return this;
    }
    
    public FacultyViewBuilder withFaculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }
    
    public FacultyViewBuilder withEmptyFaculty() {
        this.faculty = new Faculty();
        return this;
    }
    
    public FacultyViewBuilder withFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
        if (faculties != null) {
            this.totalCount = faculties.size();
        }
        return this;
    }
    
    public FacultyViewBuilder withUniversities(List<University> universities) {
        this.universities = universities;
        return this;
    }
    
    public FacultyViewBuilder withFormAction(String formAction) {
        this.formAction = formAction;
        return this;
    }
    
    public FacultyViewBuilder asCreateMode() {
        this.isCreate = true;
        this.isEdit = false;
        this.isDelete = false;
        return this;
    }
    
    public FacultyViewBuilder asEditMode() {
        this.isCreate = false;
        this.isEdit = true;
        this.isDelete = false;
        return this;
    }
    
    public FacultyViewBuilder asDeleteMode() {
        this.isCreate = false;
        this.isEdit = false;
        this.isDelete = true;
        return this;
    }
    
    public String buildListView() {
        validateModel();
        addCommonAttributes();
        
        if (faculties != null) {
            model.addAttribute("faculties", faculties);
        }
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("viewType", "list");
        
        return "faculties/index";
    }
    
    public String buildCreateView() {
        validateModel();
        addCommonAttributes();
        
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
        }
        if (universities != null) {
            model.addAttribute("universities", universities);
        }
        
        model.addAttribute("isCreate", true);
        model.addAttribute("viewType", "create");
        
        if (formAction != null) {
            model.addAttribute("formAction", formAction);
        }
        
        return "faculties/create";
    }
    
    public String buildEditView() {
        validateModel();
        addCommonAttributes();
        
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
        }
        if (universities != null) {
            model.addAttribute("universities", universities);
        }
        
        model.addAttribute("isEdit", true);
        model.addAttribute("viewType", "edit");
        
        if (formAction != null) {
            model.addAttribute("formAction", formAction);
        }
        
        return "faculties/edit";
    }
    
    public String buildDeleteView() {
        validateModel();
        addCommonAttributes();
        
        if (faculty != null) {
            model.addAttribute("faculty", faculty);
        }
        
        model.addAttribute("isDelete", true);
        model.addAttribute("viewType", "delete");
        
        return "faculties/delete";
    }
    
    private void validateModel() {
        if (model == null) {
            throw new IllegalStateException("Model is required for building view");
        }
    }
    
    private void addCommonAttributes() {
        if (pageTitle != null) {
            model.addAttribute("pageTitle", pageTitle);
        }
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        if (warningMessage != null) {
            model.addAttribute("warningMessage", warningMessage);
        }
        
        // Add breadcrumb information
        model.addAttribute("breadcrumbs", createBreadcrumbs());
    }
    
    private String createBreadcrumbs() {
        if (isCreate) {
            return "Home > Faculties > Create New Faculty";
        } else if (isEdit && faculty != null) {
            return "Home > Faculties > Edit > " + faculty.getFacultyName();
        } else if (isDelete && faculty != null) {
            return "Home > Faculties > Delete > " + faculty.getFacultyName();
        } else {
            return "Home > Faculties";
        }
    }
} 
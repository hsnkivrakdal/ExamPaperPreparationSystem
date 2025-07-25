package com.example.eppms.builders;

import com.example.eppms.models.Faculty;
import com.example.eppms.models.University;
import com.example.eppms.services.FacultyService;
import com.example.eppms.services.UniversityService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FacultyOperationBuilder {
    private FacultyService facultyService;
    private UniversityService universityService;
    private RedirectAttributes redirectAttributes;
    
    private FacultyOperationBuilder() {}
    
    public static FacultyOperationBuilder create() {
        return new FacultyOperationBuilder();
    }
    
    public FacultyOperationBuilder withFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
        return this;
    }
    
    public FacultyOperationBuilder withUniversityService(UniversityService universityService) {
        this.universityService = universityService;
        return this;
    }
    
    public FacultyOperationBuilder withRedirectAttributes(RedirectAttributes redirectAttributes) {
        this.redirectAttributes = redirectAttributes;
        return this;
    }
    
    public FacultyOperationResult createFaculty(Faculty faculty) {
        try {
            validateServicesForCRUD();
            validateFacultyForCreate(faculty);
            
            // Null safety check for university
            if (faculty.getUniversity() == null) {
                return FacultyOperationResult.failure("University is required");
            }
            
            if (faculty.getUniversity().getId() == null) {
                return FacultyOperationResult.failure("University ID is required");
            }
            
            // Set university if only ID is provided
            University university = universityService.getById(faculty.getUniversity().getId());
            if (university == null) {
                return FacultyOperationResult.failure("Selected university not found with ID: " + faculty.getUniversity().getId());
            }
            faculty.setUniversity(university);
            
            facultyService.add(faculty);
            
            return FacultyOperationResult.success(
                faculty, 
                "Faculty '" + faculty.getFacultyName() + "' created successfully"
            );
            
        } catch (Exception e) {
            return FacultyOperationResult.failure(
                "Failed to create faculty: " + e.getMessage()
            );
        }
    }
    
    public FacultyOperationResult updateFaculty(Faculty faculty) {
        try {
            validateServicesForCRUD();
            validateFacultyForUpdate(faculty);
            
            // Verify faculty exists
            Faculty existingFaculty = facultyService.getById(faculty.getId());
            if (existingFaculty == null) {
                return FacultyOperationResult.failure("Faculty not found with ID: " + faculty.getId());
            }
            
            // Null safety check for university
            if (faculty.getUniversity() == null) {
                return FacultyOperationResult.failure("University is required");
            }
            
            if (faculty.getUniversity().getId() == null) {
                return FacultyOperationResult.failure("University ID is required");
            }
            
            // Set university if only ID is provided
            University university = universityService.getById(faculty.getUniversity().getId());
            if (university == null) {
                return FacultyOperationResult.failure("Selected university not found with ID: " + faculty.getUniversity().getId());
            }
            faculty.setUniversity(university);
            
            facultyService.update(faculty);
            
            return FacultyOperationResult.success(
                faculty, 
                "Faculty '" + faculty.getFacultyName() + "' updated successfully"
            );
            
        } catch (Exception e) {
            return FacultyOperationResult.failure(
                "Failed to update faculty: " + e.getMessage()
            );
        }
    }
    
    public FacultyOperationResult deleteFaculty(Integer id) {
        try {
            validateServicesForCRUD();
            
            if (id == null) {
                return FacultyOperationResult.failure("Faculty ID is required");
            }
            
            Faculty faculty = facultyService.getById(id);
            if (faculty == null) {
                return FacultyOperationResult.failure("Faculty not found with ID: " + id);
            }
            
            String facultyName = faculty.getFacultyName();
            
            // Check for dependencies
            if (hasDepartments(faculty)) {
                return FacultyOperationResult.failure(
                    "Cannot delete faculty '" + facultyName + "' because it has existing departments"
                );
            }
            
            facultyService.deleteById(id);
            
            return FacultyOperationResult.success(
                null, 
                "Faculty '" + facultyName + "' deleted successfully"
            );
            
        } catch (Exception e) {
            return FacultyOperationResult.failure(
                "Failed to delete faculty: " + e.getMessage()
            );
        }
    }
    
    public FacultyOperationResult getFacultyById(Integer id) {
        try {
            validateServicesForRead();
            
            if (id == null) {
                return FacultyOperationResult.failure("Faculty ID is required");
            }
            
            Faculty faculty = facultyService.getById(id);
            if (faculty == null) {
                return FacultyOperationResult.failure("Faculty not found with ID: " + id);
            }
            
            return FacultyOperationResult.success(faculty, "Faculty found successfully");
            
        } catch (Exception e) {
            return FacultyOperationResult.failure(
                "Failed to retrieve faculty: " + e.getMessage()
            );
        }
    }
    
    private void validateServicesForCRUD() {
        if (facultyService == null) {
            throw new IllegalStateException("FacultyService is required for CRUD operations");
        }
        if (universityService == null) {
            throw new IllegalStateException("UniversityService is required for CRUD operations");
        }
    }
    
    private void validateServicesForRead() {
        if (facultyService == null) {
            throw new IllegalStateException("FacultyService is required for read operations");
        }
    }
    
    private void validateFacultyForCreate(Faculty faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException("Faculty object is required");
        }
        if (faculty.getFacultyName() == null || faculty.getFacultyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Faculty name is required and cannot be empty");
        }
        if (faculty.getFacultyName().length() > 100) {
            throw new IllegalArgumentException("Faculty name cannot exceed 100 characters");
        }
        // University validation will be done separately for better error messages
    }
    
    private void validateFacultyForUpdate(Faculty faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException("Faculty object is required");
        }
        if (faculty.getId() == null) {
            throw new IllegalArgumentException("Faculty ID is required for update");
        }
        if (faculty.getFacultyName() == null || faculty.getFacultyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Faculty name is required and cannot be empty");
        }
        if (faculty.getFacultyName().length() > 100) {
            throw new IllegalArgumentException("Faculty name cannot exceed 100 characters");
        }
        // University validation will be done separately for better error messages
    }
    
    private boolean hasDepartments(Faculty faculty) {
        return faculty.getDepartments() != null && !faculty.getDepartments().isEmpty();
    }
} 
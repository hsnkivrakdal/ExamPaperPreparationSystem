package com.example.eppms.builders;

import com.example.eppms.models.Faculty;

public class FacultyOperationResult {
    private final boolean success;
    private final String message;
    private final Faculty faculty;
    private final Exception exception;
    
    private FacultyOperationResult(boolean success, String message, Faculty faculty, Exception exception) {
        this.success = success;
        this.message = message;
        this.faculty = faculty;
        this.exception = exception;
    }
    
    public static FacultyOperationResult success(Faculty faculty, String message) {
        return new FacultyOperationResult(true, message, faculty, null);
    }
    
    public static FacultyOperationResult failure(String message) {
        return new FacultyOperationResult(false, message, null, null);
    }
    
    public static FacultyOperationResult failure(String message, Exception exception) {
        return new FacultyOperationResult(false, message, null, exception);
    }
    
    // Getters
    public boolean isSuccess() { 
        return success; 
    }
    
    public boolean isFailure() { 
        return !success; 
    }
    
    public String getMessage() { 
        return message; 
    }
    
    public Faculty getFaculty() { 
        return faculty; 
    }
    
    public Exception getException() { 
        return exception; 
    }
    
    public boolean hasFaculty() {
        return faculty != null;
    }
} 
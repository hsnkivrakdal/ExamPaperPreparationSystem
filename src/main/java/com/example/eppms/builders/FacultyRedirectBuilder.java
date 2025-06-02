package com.example.eppms.builders;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FacultyRedirectBuilder {
    private RedirectAttributes redirectAttributes;
    private String successMessage;
    private String errorMessage;
    private String warningMessage;
    private String infoMessage;
    
    private FacultyRedirectBuilder() {}
    
    public static FacultyRedirectBuilder create() {
        return new FacultyRedirectBuilder();
    }
    
    public FacultyRedirectBuilder withRedirectAttributes(RedirectAttributes redirectAttributes) {
        this.redirectAttributes = redirectAttributes;
        return this;
    }
    
    public FacultyRedirectBuilder withSuccessMessage(String message) {
        this.successMessage = message;
        return this;
    }
    
    public FacultyRedirectBuilder withErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }
    
    public FacultyRedirectBuilder withWarningMessage(String message) {
        this.warningMessage = message;
        return this;
    }
    
    public FacultyRedirectBuilder withInfoMessage(String message) {
        this.infoMessage = message;
        return this;
    }
    
    public String buildSuccessRedirect(String redirectUrl) {
        if (redirectAttributes != null) {
            if (successMessage != null) {
                redirectAttributes.addFlashAttribute("successMessage", successMessage);
            }
            if (infoMessage != null) {
                redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
            }
        }
        return "redirect:" + redirectUrl;
    }
    
    public String buildErrorRedirect(String redirectUrl) {
        if (redirectAttributes != null) {
            if (errorMessage != null) {
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            }
            if (warningMessage != null) {
                redirectAttributes.addFlashAttribute("warningMessage", warningMessage);
            }
        }
        return "redirect:" + redirectUrl;
    }
    
    public String buildWarningRedirect(String redirectUrl) {
        if (redirectAttributes != null && warningMessage != null) {
            redirectAttributes.addFlashAttribute("warningMessage", warningMessage);
        }
        return "redirect:" + redirectUrl;
    }
    
    public String buildInfoRedirect(String redirectUrl) {
        if (redirectAttributes != null && infoMessage != null) {
            redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
        }
        return "redirect:" + redirectUrl;
    }
    
    public String buildRedirectWithResult(FacultyOperationResult result, String successUrl, String errorUrl) {
        if (result.isSuccess()) {
            return withSuccessMessage(result.getMessage())
                    .buildSuccessRedirect(successUrl);
        } else {
            return withErrorMessage(result.getMessage())
                    .buildErrorRedirect(errorUrl);
        }
    }
    
    // Predefined redirects for common Faculty operations
    public String buildCreateSuccessRedirect() {
        return buildSuccessRedirect("/faculties/list");
    }
    
    public String buildCreateErrorRedirect() {
        return buildErrorRedirect("/faculties/create");
    }
    
    public String buildUpdateSuccessRedirect() {
        return buildSuccessRedirect("/faculties/list");
    }
    
    public String buildUpdateErrorRedirect(Integer facultyId) {
        return buildErrorRedirect("/faculties/edit/" + facultyId);
    }
    
    public String buildDeleteSuccessRedirect() {
        return buildSuccessRedirect("/faculties/list");
    }
    
    public String buildDeleteErrorRedirect() {
        return buildErrorRedirect("/faculties/list");
    }
    
    public String buildNotFoundRedirect() {
        return withErrorMessage("Faculty not found")
                .buildErrorRedirect("/faculties/list");
    }
} 
package fr.eseo.poo.counter.countercalories.controller.pannel;

import fr.eseo.poo.counter.countercalories.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller for the inscription (registration) panel.
 * 
 * FIXED VERSION - Validates input before creating User objects.
 */
public class InscriptionPannel {
    
    // JavaFX TextFields (would be injected via @FXML in real application)
    private TextField nameField;
    private TextField emailField;
    private TextField ageField;
    private TextField weightField;
    private TextField heightField;
    
    /**
     * Constructor for the controller.
     * In a real JavaFX application, fields would be annotated with @FXML
     * and would be automatically injected by the FXMLLoader.
     */
    public InscriptionPannel() {
        // Initialize fields (in real app, this happens via FXML injection)
        this.nameField = new TextField();
        this.emailField = new TextField();
        this.ageField = new TextField();
        this.weightField = new TextField();
        this.heightField = new TextField();
    }
    
    /**
     * Method called when user clicks the "Add User" button.
     * This is the FIXED version (line 71 area where User constructor was called).
     * 
     * ORIGINAL PROBLEM: This method was calling the User constructor with
     * potentially null/empty strings from text fields, causing NumberFormatException.
     */
    public void addNewUser() {
        // Get values from text fields
        String name = nameField.getText();
        String email = emailField.getText();
        String ageStr = ageField.getText();
        String weightStr = weightField.getText();
        String heightStr = heightField.getText();
        
        // VALIDATION STEP 1: Check for empty required fields
        if (name == null || name.trim().isEmpty()) {
            showError("Name is required", "Please enter your name.");
            return;
        }
        
        if (email == null || email.trim().isEmpty()) {
            showError("Email is required", "Please enter your email address.");
            return;
        }
        
        if (ageStr == null || ageStr.trim().isEmpty()) {
            showError("Age is required", "Please enter your age.");
            return;
        }
        
        if (weightStr == null || weightStr.trim().isEmpty()) {
            showError("Weight is required", "Please enter your weight.");
            return;
        }
        
        if (heightStr == null || heightStr.trim().isEmpty()) {
            showError("Height is required", "Please enter your height.");
            return;
        }
        
        // VALIDATION STEP 2: Validate numeric fields
        if (!isValidInteger(ageStr)) {
            showError("Invalid age", "Age must be a valid number.");
            return;
        }
        
        if (!isValidInteger(weightStr)) {
            showError("Invalid weight", "Weight must be a valid number.");
            return;
        }
        
        if (!isValidInteger(heightStr)) {
            showError("Invalid height", "Height must be a valid number.");
            return;
        }
        
        // VALIDATION STEP 3: Create User object with validated input
        try {
            // LINE 71 - This was causing the crash before the fix
            User newUser = new User(name, email, ageStr, weightStr, heightStr);
            
            // Save the user (to database, list, etc.)
            saveUser(newUser);
            
            // Show success message
            // NOTE: In production, use a proper logging framework (SLF4J, Log4j2) instead of System.out
            System.out.println("Nouveau utilisateur ajouté !");
            showSuccess("Success", "User added successfully!");
            
            // Clear the form
            clearForm();
            
        } catch (IllegalArgumentException e) {
            // Handle any remaining validation errors from User constructor
            showError("Validation Error", e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected errors
            showError("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Validate that a string can be parsed as an integer.
     * 
     * @param value The string to validate
     * @return true if the value is a valid integer, false otherwise
     */
    private boolean isValidInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        
        try {
            Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Save the user to database or storage.
     * This is a placeholder - implement actual storage logic here.
     */
    private void saveUser(User user) {
        // TODO: Implement actual database/storage logic
        // NOTE: In production, use a proper logging framework (SLF4J, Log4j2) instead of System.out
        System.out.println("Saving user: " + user);
    }
    
    /**
     * Clear all form fields after successful submission.
     */
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        ageField.clear();
        weightField.clear();
        heightField.clear();
    }
    
    /**
     * Show an error alert dialog to the user.
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Show a success alert dialog to the user.
     */
    private void showSuccess(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Setters for testing purposes (to inject mock TextFields)
    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }
    
    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }
    
    public void setAgeField(TextField ageField) {
        this.ageField = ageField;
    }
    
    public void setWeightField(TextField weightField) {
        this.weightField = weightField;
    }
    
    public void setHeightField(TextField heightField) {
        this.heightField = heightField;
    }
}

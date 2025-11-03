# Before and After: NumberFormatException Fix

## The Problem

When users tried to register through the inscription form, the application would crash if any numeric field (age, weight, height) was left empty.

### Error Message
```
Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
Caused by: java.lang.NumberFormatException: Cannot parse null string
	at java.base/java.lang.Integer.parseInt(Integer.java:630)
	at fr.eseo.poo.counter.countercalories.model.User.<init>(User.java:33)
	at fr.eseo.poo.counter.countercalories.controller.pannel.InscriptionPannel.addNewUser(InscriptionPannel.java:71)
```

---

## BEFORE - Problematic Code ❌

### User.java (Original - Line 33)
```java
package fr.eseo.poo.counter.countercalories.model;

public class User {
    private String name;
    private String email;
    private int age;
    private int weight;
    private int height;
    
    public User(String name, String email, String ageStr, 
                String weightStr, String heightStr) {
        this.name = name;
        this.email = email;
        
        // ❌ PROBLEM: These lines crash when ageStr, weightStr, or heightStr is null or empty
        this.age = Integer.parseInt(ageStr);        // Line 33 - CRASH HERE!
        this.weight = Integer.parseInt(weightStr);
        this.height = Integer.parseInt(heightStr);
    }
    
    // getters and setters...
}
```

**Why it crashes:**
- `Integer.parseInt(null)` throws `NumberFormatException`
- `Integer.parseInt("")` throws `NumberFormatException`
- `Integer.parseInt("   ")` throws `NumberFormatException`

### InscriptionPannel.java (Original - Line 71)
```java
package fr.eseo.poo.counter.countercalories.controller.pannel;

import fr.eseo.poo.counter.countercalories.model.User;

public class InscriptionPannel {
    private TextField nameField;
    private TextField emailField;
    private TextField ageField;
    private TextField weightField;
    private TextField heightField;
    
    public void addNewUser() {
        // ❌ PROBLEM: No validation - gets values directly from form
        String name = nameField.getText();
        String email = emailField.getText();
        String ageStr = ageField.getText();       // Could be null or empty!
        String weightStr = weightField.getText(); // Could be null or empty!
        String heightStr = heightField.getText(); // Could be null or empty!
        
        // ❌ PROBLEM: No validation before creating User
        User newUser = new User(name, email, ageStr, weightStr, heightStr); // Line 71 - CRASH!
        
        System.out.println("Nouveau utilisateur ajouté !");
    }
}
```

**Why it crashes:**
- No validation of user input
- Empty form fields result in null or empty strings
- Null/empty strings passed directly to User constructor
- User constructor crashes when trying to parse them

---

## AFTER - Fixed Code ✅

### User.java (Fixed)
```java
package fr.eseo.poo.counter.countercalories.model;

public class User {
    private String name;
    private String email;
    private int age;
    private int weight;
    private int height;
    
    public User(String name, String email, String ageStr, 
                String weightStr, String heightStr) {
        // ✅ Validate name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
        
        // ✅ Validate email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = email.trim();
        
        // ✅ FIXED: Safe parsing with validation
        this.age = parseIntSafely(ageStr, "Age");
        this.weight = parseIntSafely(weightStr, "Weight");
        this.height = parseIntSafely(heightStr, "Height");
    }
    
    // ✅ NEW: Helper method for safe integer parsing
    private int parseIntSafely(String value, String fieldName) {
        // Check for null or empty
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        
        // Try to parse, with clear error message if it fails
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                fieldName + " must be a valid number: " + value
            );
        }
    }
    
    // getters and setters...
}
```

**What changed:**
- ✅ Added null/empty string checks before parsing
- ✅ Created `parseIntSafely()` helper method
- ✅ Throws `IllegalArgumentException` with clear error messages
- ✅ Trims whitespace from input values
- ✅ No more NumberFormatException crashes!

### InscriptionPannel.java (Fixed)
```java
package fr.eseo.poo.counter.countercalories.controller.pannel;

import fr.eseo.poo.counter.countercalories.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InscriptionPannel {
    private TextField nameField;
    private TextField emailField;
    private TextField ageField;
    private TextField weightField;
    private TextField heightField;
    
    public void addNewUser() {
        // Get values from form
        String name = nameField.getText();
        String email = emailField.getText();
        String ageStr = ageField.getText();
        String weightStr = weightField.getText();
        String heightStr = heightField.getText();
        
        // ✅ NEW: Validate required fields
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
        
        // ✅ NEW: Validate numeric format
        if (!isValidInteger(ageStr)) {
            showError("Invalid age", "Age must be a valid number.");
            return;
        }
        
        // ✅ NEW: Try-catch for graceful error handling
        try {
            User newUser = new User(name, email, ageStr, weightStr, heightStr);
            saveUser(newUser);
            System.out.println("Nouveau utilisateur ajouté !");
            showSuccess("Success", "User added successfully!");
            clearForm();
        } catch (IllegalArgumentException e) {
            showError("Validation Error", e.getMessage());
        }
    }
    
    // ✅ NEW: Helper method to validate integer strings
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
    
    // ✅ NEW: Show error dialog to user
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // ✅ NEW: Show success dialog
    private void showSuccess(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Other methods...
}
```

**What changed:**
- ✅ Added validation before creating User objects
- ✅ Created `isValidInteger()` helper method
- ✅ Added try-catch block for graceful error handling
- ✅ Shows user-friendly error dialogs instead of crashing
- ✅ Validates both empty fields and invalid number formats

---

## Comparison Summary

| Aspect | Before ❌ | After ✅ |
|--------|----------|---------|
| **Empty field handling** | Crashes immediately | Shows helpful error message |
| **Invalid number (e.g., "abc")** | Crashes | Shows "must be a valid number" error |
| **User experience** | App crashes, loses data | Clear error messages, no data loss |
| **Error type** | Uncaught NumberFormatException | Caught IllegalArgumentException |
| **Error location** | Stack trace only | User-friendly dialog |
| **Input trimming** | No whitespace handling | Automatic trimming of whitespace |
| **Validation layers** | None | Both controller and model validate |

---

## Test Results

Running the test suite shows all scenarios are now handled correctly:

```
Test 1: Creating user with valid inputs...
  ✅ SUCCESS: User{name='John Doe', email='john@example.com', age=25, weight=70, height=175}

Test 2: Creating user with null age (should fail gracefully)...
  ✅ SUCCESS: Caught expected exception: Age cannot be empty

Test 3: Creating user with empty weight (should fail gracefully)...
  ✅ SUCCESS: Caught expected exception: Weight cannot be empty

Test 4: Creating user with invalid number format (should fail gracefully)...
  ✅ SUCCESS: Caught expected exception: Age must be a valid number: abc

Test 5: Creating user with whitespace-only height (should fail gracefully)...
  ✅ SUCCESS: Caught expected exception: Height cannot be empty

Test 6: Creating user with valid inputs containing whitespace...
  ✅ SUCCESS: User{name='Diana Prince', email='diana@example.com', age=35, weight=65, height=168}

✅ All tests completed!
```

---

## How to Apply This Fix

1. **Copy the fixed User.java** to your project's model package
2. **Copy the fixed InscriptionPannel.java** to your controller package
3. **Test thoroughly** with empty fields and invalid inputs
4. **Verify** error messages appear correctly in your JavaFX UI

The fix ensures your application handles user input errors gracefully instead of crashing!

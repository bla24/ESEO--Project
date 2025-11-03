# Fix for NumberFormatException in Counter-Calories Application

## Problem Description

The application crashes with a `NumberFormatException: Cannot parse null string` when trying to add a new user through the inscription panel.

### Stack Trace
```
Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
    ...
Caused by: java.lang.NumberFormatException: Cannot parse null string
	at java.base/java.lang.Integer.parseInt(Integer.java:630)
	at java.base/java.lang.Integer.parseInt(Integer.java:786)
	at fr.eseo.poo.counter.countercalories.model.User.<init>(User.java:33)
	at fr.eseo.poo.counter.countercalories.controller.pannel.InscriptionPannel.addNewUser(InscriptionPannel.java:71)
```

## Root Cause

In the `User.java` class constructor (line 33), the code attempts to parse a string to an integer without checking if the string is null or empty. This happens when:
1. A user leaves a numeric form field empty in the inscription panel
2. The empty field value (null or empty string) is passed to the User constructor
3. The constructor tries to parse it with `Integer.parseInt()`, which throws an exception

## Example of Problematic Code

```java
// User.java - BEFORE FIX (line 33)
public User(String name, String email, String ageStr, String weightStr) {
    this.name = name;
    this.email = email;
    this.age = Integer.parseInt(ageStr);  // ← CRASHES if ageStr is null or empty
    this.weight = Integer.parseInt(weightStr);  // ← CRASHES if weightStr is null or empty
}
```

## Solution

Add validation to check for null or empty strings before parsing, and provide appropriate handling:

### Option 1: Use Default Values

```java
// User.java - AFTER FIX
public User(String name, String email, String ageStr, String weightStr) {
    this.name = name;
    this.email = email;
    
    // Safely parse age with default value if null/empty
    if (ageStr != null && !ageStr.trim().isEmpty()) {
        this.age = Integer.parseInt(ageStr.trim());
    } else {
        this.age = 0;  // or throw an exception if age is required
    }
    
    // Safely parse weight with default value if null/empty
    if (weightStr != null && !weightStr.trim().isEmpty()) {
        this.weight = Integer.parseInt(weightStr.trim());
    } else {
        this.weight = 0;  // or throw an exception if weight is required
    }
}
```

### Option 2: Validate in the Controller

Alternatively, validate the input in `InscriptionPannel.java` before creating the User object:

```java
// InscriptionPannel.java - addNewUser method
public void addNewUser() {
    String name = nameField.getText();
    String email = emailField.getText();
    String ageStr = ageField.getText();
    String weightStr = weightField.getText();
    
    // Validate inputs before creating User
    if (ageStr == null || ageStr.trim().isEmpty()) {
        showError("Age is required");
        return;
    }
    
    if (weightStr == null || weightStr.trim().isEmpty()) {
        showError("Weight is required");
        return;
    }
    
    try {
        User newUser = new User(name, email, ageStr, weightStr);
        // Add user to database/list
        System.out.println("Nouveau utilisateur ajouté !");
    } catch (NumberFormatException e) {
        showError("Please enter valid numbers for age and weight");
    }
}
```

### Option 3: Use Helper Method

Create a safe parsing helper method:

```java
// User.java
private int parseIntSafely(String value, int defaultValue) {
    if (value == null || value.trim().isEmpty()) {
        return defaultValue;
    }
    try {
        return Integer.parseInt(value.trim());
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

public User(String name, String email, String ageStr, String weightStr) {
    this.name = name;
    this.email = email;
    this.age = parseIntSafely(ageStr, 0);
    this.weight = parseIntSafely(weightStr, 0);
}
```

## Recommended Approach

**Combine both validation approaches:**

1. **In the Controller (InscriptionPannel.java)**: Validate user input and show clear error messages
2. **In the Model (User.java)**: Add defensive checks to prevent crashes even if invalid data gets through

This follows the principle of "fail fast, fail gracefully" and provides better user experience.

## Additional Improvements

1. **Add input type validation in FXML**: Use `TextField` constraints to only allow numeric input
2. **Use JavaFX Validation Framework**: Libraries like ControlsFX provide built-in validation
3. **Consider using Integer instead of int**: This allows null values for optional fields
4. **Use proper logging**: Replace `System.out.println` with SLF4J or Log4j2 for production code
5. **Use JUnit for testing**: Proper testing frameworks provide better test organization than main methods
6. **Fix package naming**: Consider renaming "pannel" to "panel" (correct spelling) in a future refactor
7. **Builder pattern**: For constructors with many parameters, consider using the Builder pattern for better API design

## Testing

After applying the fix, test with:
- ✓ Empty age field
- ✓ Empty weight field
- ✓ Both fields empty
- ✓ Non-numeric values
- ✓ Negative numbers
- ✓ Very large numbers
- ✓ Valid numeric inputs

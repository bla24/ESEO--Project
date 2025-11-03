# Counter Calories - NumberFormatException Fix Example

This directory contains example code demonstrating the fix for the NumberFormatException error in the Counter Calories application.

## Problem

The application was crashing with this error:

```
Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
Caused by: java.lang.NumberFormatException: Cannot parse null string
	at User.<init>(User.java:33)
	at InscriptionPannel.addNewUser(InscriptionPannel.java:71)
```

## Root Cause

The `User` class constructor was attempting to parse string values to integers using `Integer.parseInt()` without checking if the strings were null or empty. When users left form fields blank, null or empty strings were passed to the constructor, causing the application to crash.

## Solution

This example demonstrates the proper way to handle user input:

### 1. Model Layer (User.java)

The `User` class now includes:
- **Input validation**: Checks for null and empty strings before parsing
- **Helper methods**: `parseIntSafely()` to handle parsing with proper error messages
- **Clear error messages**: Throws `IllegalArgumentException` with descriptive messages
- **Alternative constructor**: Supports default values for optional fields

### 2. Controller Layer (InscriptionPannel.java)

The `InscriptionPannel` controller now includes:
- **Pre-validation**: Validates all inputs before creating the User object
- **User feedback**: Shows clear error dialogs for invalid input
- **Try-catch blocks**: Catches and handles any remaining exceptions gracefully
- **Helper method**: `isValidInteger()` to check if a string can be parsed

## Key Changes

### Before (Problematic Code)

```java
// User.java - Line 33
this.age = Integer.parseInt(ageStr);  // ❌ Crashes on null/empty string
```

```java
// InscriptionPannel.java - Line 71
User newUser = new User(name, email, ageStr, weightStr, heightStr);  // ❌ No validation
```

### After (Fixed Code)

```java
// User.java - Lines 33-40
this.age = parseIntSafely(ageStr, "Age");  // ✅ Safe parsing with validation

private int parseIntSafely(String value, String fieldName) {
    if (value == null || value.trim().isEmpty()) {
        throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
    try {
        return Integer.parseInt(value.trim());
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException(fieldName + " must be a valid number");
    }
}
```

```java
// InscriptionPannel.java - Lines 50-100
// Validate inputs first
if (ageStr == null || ageStr.trim().isEmpty()) {
    showError("Age is required", "Please enter your age.");
    return;
}

if (!isValidInteger(ageStr)) {
    showError("Invalid age", "Age must be a valid number.");
    return;
}

// Then create User object
try {
    User newUser = new User(name, email, ageStr, weightStr, heightStr);
    saveUser(newUser);
    showSuccess("Success", "User added successfully!");
} catch (IllegalArgumentException e) {
    showError("Validation Error", e.getMessage());
}
```

## Testing the Fix

The fixed code handles all these cases properly:

1. ✅ Empty age field → Shows "Age is required" error
2. ✅ Empty weight field → Shows "Weight is required" error
3. ✅ Empty height field → Shows "Height is required" error
4. ✅ Non-numeric age (e.g., "abc") → Shows "Age must be a valid number" error
5. ✅ Valid inputs → Creates user successfully

## How to Apply This Fix

If you have the counter-calories project locally:

1. **Update User.java**:
   - Replace the constructor with the version from this example
   - Add the `parseIntSafely()` helper method

2. **Update InscriptionPannel.java**:
   - Add input validation before creating User objects
   - Add the `isValidInteger()` helper method
   - Add error dialogs for user feedback

3. **Test thoroughly**:
   - Try submitting the form with empty fields
   - Try entering non-numeric values
   - Verify error messages are clear and helpful

## Additional Recommendations

1. **Add UI constraints**: Configure TextFields in FXML to only accept numeric input
2. **Use validation libraries**: Consider ControlsFX or similar for advanced validation
3. **Add logging**: Replace `System.out.println` with a proper logging framework (SLF4J, Log4j2) for better maintainability
4. **Use proper testing**: Replace the main method test with JUnit or TestNG for better test organization
5. **Improve API design**: Consider using a builder pattern instead of the boolean parameter constructor overload
6. **Internationalization**: Use resource bundles for error messages

**Note**: The package name "pannel" (with double-n) matches the original code from the error stack trace. In a refactoring, you might want to rename it to "panel" (single-n) for correct spelling.

## Files in This Example

- `src/main/java/fr/eseo/poo/counter/countercalories/model/User.java` - Fixed User model class
- `src/main/java/fr/eseo/poo/counter/countercalories/controller/pannel/InscriptionPannel.java` - Fixed controller class
- `README.md` - This file

## Questions?

For more details, see the main `NUMBERFORMATEXCEPTION_FIX.md` document in the repository root.

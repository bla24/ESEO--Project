# Quick Fix Guide - NumberFormatException

## The 1-Minute Fix

If you're seeing this error:
```
NumberFormatException: Cannot parse null string at User.<init>(User.java:33)
```

### Step 1: Fix User.java

Replace line 33 area:
```java
// OLD - CRASHES:
this.age = Integer.parseInt(ageStr);

// NEW - SAFE:
this.age = parseIntSafely(ageStr, "Age");
```

Add this helper method to User class:
```java
private int parseIntSafely(String value, String fieldName) {
    if (value == null || value.trim().isEmpty()) {
        throw new IllegalArgumentException(fieldName + " cannot be empty");
    }
    try {
        return Integer.parseInt(value.trim());
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException(fieldName + " must be a valid number: " + value);
    }
}
```

### Step 2: Fix InscriptionPannel.java

Wrap line 71 area in try-catch:
```java
// OLD - NO ERROR HANDLING:
User newUser = new User(name, email, ageStr, weightStr, heightStr);
System.out.println("Nouveau utilisateur ajouté !");

// NEW - WITH ERROR HANDLING:
try {
    User newUser = new User(name, email, ageStr, weightStr, heightStr);
    saveUser(newUser);
    System.out.println("Nouveau utilisateur ajouté !");
    showSuccess("Success", "User added successfully!");
} catch (IllegalArgumentException e) {
    showError("Validation Error", e.getMessage());
}
```

Add validation before creating User:
```java
if (ageStr == null || ageStr.trim().isEmpty()) {
    showError("Age is required", "Please enter your age.");
    return;
}
```

### Done! ✅

Your app will now:
- ✅ Show error messages instead of crashing
- ✅ Handle empty form fields gracefully
- ✅ Validate numeric inputs
- ✅ Provide clear feedback to users

---

## Full Documentation

For complete details, see:
- `NUMBERFORMATEXCEPTION_FIX.md` - Complete guide with all options
- `BEFORE_AND_AFTER.md` - Side-by-side comparison
- `counter-calories-fix-example/` - Working example code with tests

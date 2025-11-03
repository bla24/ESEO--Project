package fr.eseo.poo.counter.countercalories.model;

/**
 * User model class for the Counter Calories application.
 * 
 * FIXED VERSION - Handles null and empty string inputs properly.
 */
public class User {
    private String name;
    private String email;
    private int age;
    private int weight;
    private int height;  // Assuming this might also be parsed from string
    
    /**
     * Constructor with string parameters for numeric fields.
     * This is the FIXED version that handles null/empty strings properly.
     * 
     * @param name User's name
     * @param email User's email
     * @param ageStr User's age as string (from form input)
     * @param weightStr User's weight as string (from form input)
     * @param heightStr User's height as string (from form input)
     * @throws IllegalArgumentException if required fields are invalid
     */
    public User(String name, String email, String ageStr, String weightStr, String heightStr) {
        // Validate and set name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
        
        // Validate and set email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = email.trim();
        
        // Safely parse age - LINE 33 AREA (where the original error occurred)
        this.age = parseIntSafely(ageStr, "Age");
        
        // Safely parse weight
        this.weight = parseIntSafely(weightStr, "Weight");
        
        // Safely parse height
        this.height = parseIntSafely(heightStr, "Height");
    }
    
    /**
     * Helper method to safely parse integer from string.
     * This prevents NumberFormatException for null or empty strings.
     * 
     * @param value The string value to parse
     * @param fieldName The name of the field (for error messages)
     * @return Parsed integer value
     * @throws IllegalArgumentException if the value is null, empty, or not a valid integer
     */
    private int parseIntSafely(String value, String fieldName) {
        // Check for null or empty string
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number: " + value);
        }
    }
    
    /**
     * Alternative constructor that accepts optional numeric fields.
     * Uses default values if fields are null or empty.
     * 
     * NOTE: The boolean parameter pattern is shown here as an alternative approach,
     * but in production code, consider using the Builder pattern or separate factory
     * methods (e.g., createWithDefaults()) for better API clarity.
     * 
     * @param useDefaults If true, uses default values instead of throwing exceptions
     */
    public User(String name, String email, String ageStr, String weightStr, String heightStr, 
                boolean useDefaults) {
        this.name = (name != null) ? name.trim() : "";
        this.email = (email != null) ? email.trim() : "";
        this.age = parseIntWithDefault(ageStr, 0);
        this.weight = parseIntWithDefault(weightStr, 0);
        this.height = parseIntWithDefault(heightStr, 0);
    }
    
    /**
     * Parse integer with a default value if parsing fails.
     */
    private int parseIntWithDefault(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}

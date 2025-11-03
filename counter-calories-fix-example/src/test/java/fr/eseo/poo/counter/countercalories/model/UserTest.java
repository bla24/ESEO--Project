package fr.eseo.poo.counter.countercalories.model;

/**
 * Simple test class to demonstrate that the User class fix works.
 * 
 * NOTE: This uses a simple main method for demonstration purposes.
 * In production code, use JUnit, TestNG, or another proper testing framework
 * for better test organization, reporting, and integration with build tools.
 */
public class UserTest {
    
    public static void main(String[] args) {
        System.out.println("Testing User class fixes for NumberFormatException...\n");
        
        // Test 1: Valid inputs - Should work
        test1_ValidInputs();
        
        // Test 2: Null age - Should throw IllegalArgumentException
        test2_NullAge();
        
        // Test 3: Empty weight - Should throw IllegalArgumentException
        test3_EmptyWeight();
        
        // Test 4: Invalid number format - Should throw IllegalArgumentException
        test4_InvalidNumberFormat();
        
        // Test 5: Whitespace only - Should throw IllegalArgumentException
        test5_WhitespaceOnly();
        
        // Test 6: Valid inputs with extra whitespace - Should work
        test6_ValidInputsWithWhitespace();
        
        System.out.println("\n✅ All tests completed!");
    }
    
    private static void test1_ValidInputs() {
        System.out.println("Test 1: Creating user with valid inputs...");
        try {
            User user = new User("John Doe", "john@example.com", "25", "70", "175");
            System.out.println("  ✅ SUCCESS: " + user);
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: " + e.getMessage());
        }
    }
    
    private static void test2_NullAge() {
        System.out.println("\nTest 2: Creating user with null age (should fail gracefully)...");
        try {
            User user = new User("Jane Smith", "jane@example.com", null, "60", "165");
            System.out.println("  ❌ FAILED: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
    
    private static void test3_EmptyWeight() {
        System.out.println("\nTest 3: Creating user with empty weight (should fail gracefully)...");
        try {
            User user = new User("Bob Johnson", "bob@example.com", "30", "", "180");
            System.out.println("  ❌ FAILED: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
    
    private static void test4_InvalidNumberFormat() {
        System.out.println("\nTest 4: Creating user with invalid number format (should fail gracefully)...");
        try {
            User user = new User("Alice Brown", "alice@example.com", "abc", "55", "170");
            System.out.println("  ❌ FAILED: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
    
    private static void test5_WhitespaceOnly() {
        System.out.println("\nTest 5: Creating user with whitespace-only height (should fail gracefully)...");
        try {
            User user = new User("Charlie Wilson", "charlie@example.com", "28", "75", "   ");
            System.out.println("  ❌ FAILED: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✅ SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: Wrong exception type: " + e.getClass().getSimpleName());
        }
    }
    
    private static void test6_ValidInputsWithWhitespace() {
        System.out.println("\nTest 6: Creating user with valid inputs containing whitespace...");
        try {
            User user = new User("  Diana Prince  ", " diana@example.com ", " 35 ", " 65 ", " 168 ");
            System.out.println("  ✅ SUCCESS: " + user);
        } catch (Exception e) {
            System.out.println("  ❌ FAILED: " + e.getMessage());
        }
    }
}

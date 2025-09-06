import java.time.LocalDate;

public class verifySchool {
    public static void main(String[] args) {
        School school = new School();
        school.setName("ESEO");
        school.setAddress("Angers, France");

        Student student1 = new Student(); // Assuming Student has a default constructor
        // Optionally set student details here

        boolean enrolled = school.enrollStudent(student1, LocalDate.now());
        if (enrolled) {
            System.out.println("Enregistrement réussi.");
        } else {
            System.out.println("Échec de l'enregistrement de l'étudiant.");
        }
    }
}
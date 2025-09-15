package fr.eseo.b2.sms.school;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import fr.eseo.b2.sms.school.apprenants.Student;
import fr.eseo.b2.sms.school.employees.Teacher;
/**
 * Represents a school institution with students and teachers management capabilities.
 * The class provides functionality for enrolling students, employing teachers,
 * and managing basic school information.
 */
public class School {
    /** The name of the school */
    private String name;
    /** The physical address of the school */
    private String address;
    /** Array containing all teachers employed at the school */
    private Teacher [] teachers;
    /** Array containing all students enrolled in the school */
    private Student[] students;
    /** Current count of enrolled students */
    private int numberStudentsEnrolled;
    /** Current count of employed teachers */
    private int numberTeachersEmployed;

    /**
     * Gets the school's name.
     * @return The name of the school
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the school's name.
     * @param name The new name for the school
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the school's address.
     * @return The physical address of the school
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the school's address.
     * @param address The new address for the school
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the array of teachers employed at the school.
     * @return Array of Teacher objects
     */
    public Teacher[] getTeachers() {
        return teachers;
    }

    /**
     * Sets the array of teachers.
     * @param teachers Array of Teacher objects to set
     */
    public void setTeachers(Teacher[] teachers) {
        this.teachers = teachers;
    }

    /**
     * Gets the array of enrolled students.
     * @return Array of Student objects
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * Sets the array of students.
     * @param students Array of Student objects to set
     */

    public void setStudents(Student[] students) {
        this.students = students;
    }

    /**
     * Gets the current number of enrolled students.
     * @return Count of enrolled students
     */
    public int getNumberStudentsEnrolled() {
        return numberStudentsEnrolled;
    }

    /**
     * Sets the number of enrolled students.
     * @param numberStudentsEnrolled New count of enrolled students
     */
    public void setNumberStudentsEnrolled(int numberStudentsEnrolled) {
        this.numberStudentsEnrolled = numberStudentsEnrolled;
    }

    /**
     * Gets the current number of employed teachers.
     * @return Count of employed teachers
     */

    public int getNumberTeachersEmployed() {
        return numberTeachersEmployed;
    }

    /**
     * Sets the number of employed teachers.
     * @param numberTeachersEmployed New count of employed teachers
     */
    public void setNumberTeachersEmployed(int numberTeachersEmployed) {
        this.numberTeachersEmployed = numberTeachersEmployed;
    }

    /**
     * Enrolls a new student in the school.
     * Generates and assigns a unique ID and username for the studentn before adding them to the array of students. If the array is full displays a message and does not try to add the student.
     *
     * @param student The Student object to enroll
     * @param enrollmentDate The date of enrollment
     */
    public void enrollStudent(Student student, LocalDate enrollmentDate) {
        if(numberStudentsEnrolled >= students.length) {
            System.out.println("Cannot enroll anymore students.");
            return;
        }
        int id = (enrollmentDate.getYear()%100)*100;
        id += (numberStudentsEnrolled+1);
        student.setInscriptionNumber(id);
        student.setUsername(generateStudentUsername(student));
        students[numberStudentsEnrolled] = student;
        numberStudentsEnrolled++;
        System.out.println(student.displayInformation());
    }
    
    /**
     * Enrolls a new student in the school.
     * Generates and assigns a unique ID and username for the studentn before adding them to the array of students. If the array is full displays a message and does not try to add the student.
     *
     * @param forename The Student's forename
     * @param surname The Student's forename
     * @param sateOfBirht The Student's date of birth
     * @param enrollmentDate The date of enrollment
     */
    public void enrollStudent(String forename, String surname, LocalDate dateOfBirth, LocalDate enrollmentDate) {
        if(numberStudentsEnrolled >= students.length) {
            System.out.println("Cannot enroll anymore students.");
            return;
        }
        int id = (enrollmentDate.getYear()%100)*100;
        id += (numberStudentsEnrolled+1);
        Student student = new Student(forename, surname, dateOfBirth);
        student.setInscriptionNumber(id);
        student.setUsername(generateStudentUsername(student));
        students[numberStudentsEnrolled] = student;
        numberStudentsEnrolled++;
        System.out.println(student.displayInformation());
    }
    
    

    /**
     * Employs a new teacher at the school.
     * Generates and assigns a username for the teacher, before adding them to the array of teachers. If the array is full displays a message and does not try to add the teacher.
     *
     * @param forename The Teacher's forename
     * @param surname The Teacher's forename
     * @param sateOfBirht The Teacher's date of birth
     * @param startDate The employment start date
     */
    public void employTeacher(Teacher teacher, LocalDate startDate){
        if(numberTeachersEmployed >= teachers.length) {
            System.out.println("Cannot employ anymore teachers.");
            return;
        }
        teacher.setStartDate(startDate);
        teacher.setUsername(generateTeacherUsername(teacher));
        teachers[numberTeachersEmployed] = teacher;
        numberTeachersEmployed++;
        System.out.println(teacher.displayInformation());
    }
    
    /**
     * Employs a new teacher at the school.
     * Generates and assigns a username for the teacher, before adding them to the array of teachers. If the array is full displays a message and does not try to add the teacher.
     *
     * @param teacher The Teacher object to employ
     * @param startDate The employment start date
     */
    public void employTeacher(String forename, String surname, LocalDate dateOfBirth, LocalDate startDate){
        if(numberTeachersEmployed >= teachers.length) {
            System.out.println("Cannot employ anymore teachers.");
            return;
        }
        Teacher teacher = new Teacher(forename, surname, dateOfBirth);
        teacher.setStartDate(startDate);
        teacher.setUsername(generateTeacherUsername(teacher));
        teachers[numberTeachersEmployed] = teacher;
        numberTeachersEmployed++;
        System.out.println(teacher.displayInformation());
    }

    /**
     * Displays information about all enrolled students.
     */
    public void displayStudents(){
        for(Student student : students) {
            System.out.println(student.displayInformation());
        }
    }

    /**
     * Displays information about all employed teachers.
     */
    public void displayTeachers(){
        for(Teacher teacher : teachers) {
            System.out.println(teacher.displayInformation());
        }
    }

    /**
     * Returns a formatted string containing all school information,
     * including details about teachers and students.
     *
     * @return Formatted string with school information
     */
    public String displayInformation(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(" - ");
        sb.append(this.getAddress());
        sb.append("\n");
        if(numberTeachersEmployed==0){
            System.out.println("There are no teachers employed.");
        }
        else {
            for (Teacher teacher : teachers) {
                if(teacher!=null) {
                    sb.append(teacher.displayInformation());
                    sb.append("\n");
                }
            }
        }
        if(numberStudentsEnrolled==0){
            System.out.println("There are no students enrolled.");
        }
        else {
            for (Student student : students) {
                if(student!=null) {
                    sb.append(student.displayInformation());
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Removes accents, spaces, and special characters from input string.
     *
     * @param input String to be stripped
     * @return Stripped string
     */
    private static String strip(String input){
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String deaccented = normalized.replaceAll("[^\\p{ASCII}]", "");
        String stripped = deaccented.replaceAll(" ","").replaceAll("'.'","").replaceAll("-","");
        return stripped;
    }

    /**
     * Shortens input string to specified maximum length.
     *
     * @param input String to be shortened
     * @param maxLength Maximum length of output string
     * @return Shortened string
     */
    private static String shorten(String input, int maxLength){
        return (input.length()<maxLength)?input:input.substring(0,maxLength);
    }

    /** Decimal format for teacher IDs */
    static DecimalFormat DF_TEACHER = new DecimalFormat("00");
    /** Decimal format for student IDs */
    static DecimalFormat DF_STUDENT = new DecimalFormat("000");

    /**
     * Generates a unique username for a teacher based on their name and start date.
     *
     * @param teacher Teacher object to generate username for
     * @return Generated username
     */
    private static String generateTeacherUsername(Teacher teacher){
        StringBuilder username = new StringBuilder();
        username.append(shorten(strip(teacher.getForename()),2).toLowerCase());
        username.append(shorten(strip(teacher.getSurname()),5).toLowerCase());
        username.append(DF_TEACHER.format(teacher.getStartDate().getYear()%100));
        return username.toString();
    }

    /**
     * Generates a unique username for a student based on their name and ID.
     *
     * @param student Student object to generate username for
     * @return Generated username
     */
    private static String generateStudentUsername(Student student){
        StringBuilder username = new StringBuilder();
        username.append(shorten(strip(student.getSurname()),6).toLowerCase());
        username.append(shorten(strip(student.getForename()),3).toLowerCase());
        username.append(DF_STUDENT.format(student.getInscriptionNumber()%1000));
        return username.toString();
    }
}

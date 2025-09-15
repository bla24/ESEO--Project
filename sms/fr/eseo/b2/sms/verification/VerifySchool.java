package fr.eseo.b2.sms.verification;

import java.time.LocalDate;
import fr.eseo.b2.sms.school.apprenants.Student;

public class VerifySchool {
    public static void main(String[] args) {
        School school = new School();
        school.setName("Nevermore Academy");
        school.setAddress("Jericho");
        school.setStudents(new Student[20]);
        school.setTeachers(new Teacher[20]);


        Student s1 = new Student("Jenna", "Ortega", LocalDate.of(2002,9,27));
        Student s2 = new Student("Emma", "Myers", LocalDate.of(2002,4,2));
        Student s3 = new Student("Moosa", "Mostafa", LocalDate.of(2008,2,25));
        Student s4 = new Student("Joy", "Sunday", LocalDate.of(1996,9,25));

        Teacher t1 = new Teacher("Luis", "Guzmán", LocalDate.of(1956,8,28));
        Teacher t2 = new Teacher("Catherine", "Zeta-Jones", LocalDate.of(1969,9,25));
        Teacher t3 = new Teacher("Gwendoline", "Christie", LocalDate.of(1978,10,28));
        Teacher t4 = new Teacher("Jamie", "Mc Shane", LocalDate.of(1966,7,22));

        school.enrollStudent(s1, LocalDate.of(2023,9,1));
        school.enrollStudent(s2, LocalDate.of(2022,9,2));
        school.enrollStudent(s3, LocalDate.of(2024,9,3));
        school.enrollStudent(s4, LocalDate.of(2020,9,4));
        school.employTeacher(t1, LocalDate.of(2014,9,5));
        school.employTeacher(t2, LocalDate.of(2020,9,4));
        school.employTeacher(t3, LocalDate.of(2022,9,5));
        school.employTeacher(t4, LocalDate.of(2003,9,4));

        System.out.println("\n\n\n");
        System.out.println(school.displayInformation());
    }
}

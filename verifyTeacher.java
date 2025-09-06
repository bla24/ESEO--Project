import java.time.LocalDate;

public class verifyTeacher {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher();
        teacher1.setForename("Luis");
        teacher1.setSurname("Guzmán");
        teacher1.setDateOfBirth(LocalDate.of(1956, 8, 28));
        teacher1.setStartDate(LocalDate.of(2014, 1, 1));
        teacher1.setUsername("luguzma14");

        System.out.println("Teacher Information:");
        System.out.println(teacher1.displayInformation());

        Teacher teacher2 = new Teacher();
        teacher2.setForename("Catherine");
        teacher2.setSurname("Zeta-Jones");
        teacher2.setDateOfBirth(LocalDate.of(1699, 9, 25));
        teacher2.setStartDate(LocalDate.of(2020, 1, 1));
        teacher2.setUsername("cazetaj20");

        System.out.println(teacher2.displayInformation());

        Teacher teacher3 = new Teacher();
        teacher3.setForename("Gwendoline");
        teacher3.setSurname("Christie");
        teacher3.setDateOfBirth(LocalDate.of(1978, 10, 28));
        teacher3.setStartDate(LocalDate.of(2022, 1, 1));
        teacher3.setUsername("gwchris22");

        System.out.println(teacher3.displayInformation());

        Teacher teacher4 = new Teacher();
        teacher4.setForename("Jamie");
        teacher4.setSurname("Mc Shane");
        teacher4.setDateOfBirth(LocalDate.of(1966, 7, 22));
        teacher4.setStartDate(LocalDate.of(2003, 1, 1));
        teacher4.setUsername("jamcsha03");

        System.out.println(teacher4.displayInformation());
    }
}

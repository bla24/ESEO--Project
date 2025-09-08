import java.time.LocalDate;

class VerifyStudent {
    public static void main(String[] args) {
        Student student1 = new Student("Ethan", "Herisse", LocalDate.of(2000,9,23));
        student1.setInscriptionNumber(22);
        student1.setUsername("etheriss00");
        System.out.println(student1.displayInformation());
    }
}

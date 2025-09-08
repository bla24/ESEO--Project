import java.time.LocalDate;

class VerifyTeacher {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Debbie","Harry",LocalDate.of(1945,7,1));
        teacher1.setStartDate(LocalDate.of(2005,11,2));
        teacher1.setUsername("harrydeb45");
        System.out.println(teacher1.displayInformation());
    }
}

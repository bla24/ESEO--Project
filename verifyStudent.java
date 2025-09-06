import java.time.LocalDate;

public class verifyStudent {
	public static void main (String [] args) {
		Student student1 = new Student();
		student1.setForename("Ethan");
		student1.setSurname("Herisse");
		student1.setDateOfBirth(LocalDate.of(2000,9,23));
		student1.setInscriptionNumber(22);
		student1.setUsername("etheriss00");

		System.out.println("Student Information:");
		System.out.println(student1.displayInformation());
	
		Student student2 = new Student();
		student2.setForename("Jenna");
		student2.setSurname("Ortega");
		student2.setDateOfBirth(LocalDate.of(2002,9,27));
		student2.setInscriptionNumber(2301);
		student2.setUsername("ortegajen301");

		System.out.println(student2.displayInformation());
	
		Student student3 = new Student();
		student3.setForename("Emma");
		student3.setSurname("Myers");
		student3.setDateOfBirth(LocalDate.of(2002,2,02));
		student3.setInscriptionNumber(2202);
		student3.setUsername("myersemm202");

		System.out.println(student3.displayInformation());
	
		Student student4 = new Student();
		student4.setForename("Moosa");
		student4.setSurname("Mostafa");
		student4.setDateOfBirth(LocalDate.of(2008,2,25));
		student4.setInscriptionNumber(2403);
		student4.setUsername("mostafmo403");

		System.out.println(student4.displayInformation());
	
		Student student5 = new Student();
		student5.setForename("Joy");
		student5.setSurname("sunday");
		student5.setDateOfBirth(LocalDate.of(1996,9,25));
		student5.setInscriptionNumber(2004);
		student5.setUsername("sundayjoy004");

		System.out.println(student5.displayInformation());
	
	}
}

import java.time.LocalDate;

public class School {
	private String name;
	private String address;
	private Student[] students = new Student[20];
	private int studentCount = 0;
	

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Student[] getStudents() {
			return students;
		}

		public void setStudents(Student[] students) {
			this.students = students;
		}

		public boolean enrollStudent(Student student, LocalDate enrollmentDate) {
			if (studentCount >= students.length) {
				System.out.println("Il n'y a plus de place disponible!");
				return false;
			} else {
				students[studentCount] = student;
				studentCount++;
				System.out.println("L'etudiant a ete bien inscrit le " + enrollmentDate);
				return true;
			}
		}
	}
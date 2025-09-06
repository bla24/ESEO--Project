import java.time.LocalDate;

 
public class Student {
	String Forename;
	String Surname;
	LocalDate DateOfBirth;
	int InscriptionNumber;
	String Username;
	
	String getForename(){
		return Forename;
	}

	void setForename(String Forename){
		this.Forename = Forename;
	}

	String getSurname(){
		return Surname;
	}

	void setSurname(String Surname){
		this.Surname = Surname;
	}

	LocalDate getDateOfBirth(){
		return DateOfBirth;
	}
 
	void setDateOfBirth(LocalDate DateOfBirth){
		this.DateOfBirth = DateOfBirth;
	}
 
	int getInscriptionNumber(){
		return InscriptionNumber;
	}
 
	void setInscriptionNumber(int InscriptionNumber){
		this.InscriptionNumber = InscriptionNumber;
	}
 
	String getUsername(){
		return Username;
	}
 
	void setUsername(String Username){
		this.Username = Username;
	}
 
	String displayInformation(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getForename());
		sb.append(" ");
		sb.append(this.getSurname());
		sb.append(" (");
		sb.append(this.getInscriptionNumber());
		sb.append(")\n\t");
		sb.append("Born: ");
		sb.append(this.getDateOfBirth().toString());
		sb.append("\n\t");
		sb.append("Username: ");
		sb.append(this.getUsername());
		return sb.toString();
	}
}

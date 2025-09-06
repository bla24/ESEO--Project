import java.time.LocalDate;

public class Teacher {
	String Forename;
	String Surname;
	LocalDate DateOfBirth;
	LocalDate StartDate;
	String Username;
	
	String getForename(){
		return this.Forename;
	}
	
	void setForename(String Forename){
		this.Forename = Forename;
	}
	
	String getSurname(){
		return this.Surname;
	}
	
	void setSurname(String Surname){
		this.Surname = Surname;
	}
	
	LocalDate getDateOfBirth(){
		return this.DateOfBirth;
	}
	
	void setDateOfBirth(LocalDate DateOfBirth){
		this.DateOfBirth = DateOfBirth;
	}

	LocalDate getStartDate(){
		return this.StartDate;

	}
	
	void setStartDate(LocalDate StartDate){
		this.StartDate = StartDate;
	}

	String getUsername(){
		return this.Username;
	}

	void setUsername(String Username){
		this.Username = Username;
	}

	String displayInformation(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getForename());
		sb.append(" ");
		sb.append(this.getSurname());
		sb.append("\n\t");
		sb.append("Born: ");
		sb.append(this.getDateOfBirth().toString());
		sb.append("\n\t");
		sb.append("Start Date: ");
		sb.append(this.getStartDate().toString());
		sb.append("\n\t");
		sb.append("Username: ");
		sb.append(this.getUsername());
		return sb.toString();
	}

	


}


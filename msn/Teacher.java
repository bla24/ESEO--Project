import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Represents a teacher employed at the school.
 * Contains personal information and employment details.
 */
class Teacher {
    /** Teacher's first name */
    String forename;
    /** Teacher's last name */
    String surname;
    /** Teacher's date of birth */
    LocalDate dateOfBirth;
    /** Teacher's employment start date */
    LocalDate startDate;
    /** Generated username for the teacher */
    String username;

    Teacher(String forename, String surname, LocalDate dateOfBirth) {
        this.setForename(forename);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
    }
    
    Teacher(String forename, String surname, int year, int month, int date) {
        this(forename, surname, LocalDate.of(year, month, date));
    }

    Teacher(String forename, String surname, LocalDate dateOfBirth, LocalDate startDate, String username){
        this(forename, surname, dateOfBirth);   
        this.setStartDate(startDate);
        this.setUsername(username);    
    }

    /**
     * Gets the teacher's first name.
     * @return Teacher's forename
     */
    String getForename() {
        return forename;
    }

    /**
     * Sets the teacher's first name.
     * @param forename New forename to set
     */
    void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the teacher's last name.
     * @return Teacher's surname
     */
    String getSurname() {
        return surname;
    }

    /**
     * Sets the teacher's last name.
     * @param surname New surname to set
     */
    void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the teacher's date of birth.
     * @return Teacher's date of birth
     */
    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the teacher's date of birth.
     * @param dateOfBirth New date of birth to set
     */
    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the teacher's employment start date.
     * @return Teacher's start date
     */
    LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the teacher's employment start date.
     * @param startDate New start date to set
     */
    void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the teacher's username.
     * @return Teacher's username
     */
    String getUsername() {
        return username;
    }

    /**
     * Sets the teacher's username.
     * @param username New username to set
     */
    void setUsername(String username) {
        this.username = username;
    }

    /**
     * Calculates the number of years the teacher has been employed.
     * @return Years of seniority
     */
    long seniority(){
        return Math.abs(ChronoUnit.YEARS.between(LocalDate.now(),startDate));
    }

    /**
     * Returns a formatted string containing the teacher's information.
     * @return Formatted string with teacher details
     */
    String displayInformation(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getForename());
        sb.append(" ");
        sb.append(this.getSurname());
        sb.append(" (seniority: ");
        sb.append(seniority());
        sb.append(" - start date: ");
        sb.append(this.getStartDate().toString());
        sb.append(")\n\t" );
        // new line and a tab
        sb.append("Born : ");
        sb.append(this.getDateOfBirth().toString());
        sb.append("\n\t");
        sb.append("Username : ");
        sb.append(this.getUsername());
        return sb.toString();
    }
}

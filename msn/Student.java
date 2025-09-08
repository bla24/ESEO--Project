import java.time.LocalDate;

/**
 * Represents a student enrolled in the school.
 * Contains personal information and identification details.
 */
class Student {
    /** Student's first name */
    String forename;
    /** Student's last name */
    String surname;
    /** Student's date of birth */
    LocalDate dateOfBirth;
    /** Unique student ID */
    int inscriptionNumber;
    /** Generated username for the student */
    String username;
    /** Student's Year group*/
    YearGroup yearGroup;

    Student(String forename, String surname, LocalDate dateOfBirth) {
        this.setForename(forename);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.yearGroup = YearGroup.B2;
    }
    
    Student(String forename, String surname, int year, int month, int date) {
        this(forename, surname, LocalDate.of(year, month, date));
    }

    Student(String forename, String surname, LocalDate dateOfBirth, int inscriptionNumber, String username){
        this(forename, surname, dateOfBirth);   
        this.setInscriptionNumber(inscriptionNumber);
        this.setUsername(username);    
    }

    /**
     * Gets the student's first name.
     * @return Student's forename
     */
    String getForename() {
        return forename;
    }

    /**
     * Sets the student's first name.
     * @param forename New forename to set
     */
    void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the student's last name.
     * @return Student's surname
     */
    String getSurname() {
        return surname;
    }

    /**
     * Sets the student's last name.
     * @param surname New surname to set
     */
    void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the student's date of birth.
     * @return Student's date of birth
     */
    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the student's date of birth.
     * @param dateOfBirth New date of birth to set
     */
    void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the student's Inscription number.
     * @return Student's unique Inscription number
     */
    int getInscriptionNumber() {
        return inscriptionNumber;
    }
    
    YearGroup getYearGroup(){
        return yearGroup;
    }
    
    void setYearGroup(YearGroup yearGroup){
        this.yearGroup = yearGroup;
    }
    

    /**
     * Sets the student's Inscription number.
     * @param id New Inscription number to set
     */
     void setInscriptionNumber(int inscriptionNumber) {
        this.inscriptionNumber = inscriptionNumber;
    }

    /**
     * Gets the student's username.
     * @return Student's username
     */
    String getUsername() {
        return username;
    }

    /**
     * Sets the student's username.
     * @param username New username to set
     */
    void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns a formatted string containing the student's information.
     * @return Formatted string with student details
     */
    String displayInformation(){
            StringBuilder sb = new StringBuilder();
            sb.append(this.getForename());
            sb.append(" ");
            sb.append(this.getSurname());
            sb.append(" (");
            sb.append(this.getYearGroup());
            sb.append(" - ");
            sb.append(this.getInscriptionNumber());
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

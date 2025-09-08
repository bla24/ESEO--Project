package fr.eseo.b2.sms.school.employees;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a teacher employed at the school.
 * Contains personal information and employment details.
 */
public class Teacher {
    /** Teacher's first name */
    /** Teacher's last name */
    /** Teacher's date of birth */
    /** Teacher's employment start date */
    /** Generated username for the teacher */
        private String forename;
        private String surname;
        private LocalDate dateOfBirth;
        private LocalDate startDate;
        private String username;

    public Teacher(String forename, String surname, LocalDate dateOfBirth) {
        this.setForename(forename);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
    }
    
    public Teacher(String forename, String surname, int year, int month, int date) {
        this(forename, surname, LocalDate.of(year, month, date));
    }

    public Teacher(String forename, String surname, LocalDate dateOfBirth, LocalDate startDate, String username){
        this(forename, surname, dateOfBirth);   
        this.setStartDate(startDate);
        this.setUsername(username);    
    }

    /**
     * Gets the teacher's first name.
     * @return Teacher's forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the teacher's first name.
     * @param forename New forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the teacher's last name.
     * @return Teacher's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the teacher's last name.
     * @param surname New surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the teacher's date of birth.
     * @return Teacher's date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the teacher's date of birth.
     * @param dateOfBirth New date of birth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the teacher's employment start date.
     * @return Teacher's start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the teacher's employment start date.
     * @param startDate New start date to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the teacher's username.
     * @return Teacher's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the teacher's username.
     * @param username New username to set
     */
    public void setUsername(String username) {
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
    public String displayInformation(){
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

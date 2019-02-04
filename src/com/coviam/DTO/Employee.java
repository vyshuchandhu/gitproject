package com.coviam.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Employee {
    String firstName;
    String lastName;
    String middleName;
    Date dateOfBirth;
    String title;
    String departmentName;
    double experience;

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @XmlElement
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @XmlElement
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @XmlElement
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getExperience() {
        return experience;
    }

    @XmlElement
    public void setExperience(double experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "ESOP{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", title='" + title + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", experience=" + experience +
                '}';
    }

    @Deprecated
    public String deprecatedMethod() {
        return "Test";
    }

}

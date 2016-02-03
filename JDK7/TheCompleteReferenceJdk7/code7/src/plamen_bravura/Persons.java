package plamen_bravura;

import java.util.Date;

/**
 * Created by plamen on 17/06/2014.
 */
public class Persons implements Comparable<Persons>{
    private String firName;
    private String surName;
    private Date dob;

    public Persons(){}

    public Persons(String fName, String sName, Date dob){
        this.firName = fName;
        this.surName = sName;
        this.dob = dob;
    }

    public String getFirName() {
        return firName;
    }

    public void setFirName(String firName) {
        this.firName = firName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Persons))
            return false;
        Persons persons = (Persons)o;
        return persons.getFirName().equals(getFirName()) && persons.getSurName().equals(getSurName()) && persons.getDob().equals(getDob());
    }

    @Override
    public int hashCode() {

        int result = getFirName().hashCode();
        result = 31*result + getSurName().hashCode();
        result = 31*result + getDob().hashCode();

        return result;
    }

    @Override
    public int compareTo(Persons o) {
        int n = getSurName().compareTo(o.getSurName());
        return (n != 0 ? n : getFirName().compareTo(o.getFirName()));

    }

    @Override
    public String toString() {
        return "Persons{" +
                "firName='" + firName + '\'' +
                ", surName='" + surName + '\'' +
                ", dob=" + dob +
                '}';
    }
}

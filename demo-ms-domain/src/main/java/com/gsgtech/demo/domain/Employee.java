
package com.gsgtech.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ildemaro-medina
 */
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@Column(name="first_name",nullable=false)
    private String firstName;
	
	@Column(name="last_name",nullable=false)
    private String lastName;
	
	@Column(name="birth_date", nullable=true)
    private String birthDate;
    
    public Employee(){
        
    }

    public Employee(int id, String firstName, String lastName, String birthDate){
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
    }    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    
    public String getLastName() {
        return lastName;
    }

    public void setLast_name(String last_name) {
        this.lastName = last_name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthdate) {
        this.birthDate = birthdate;
    }
    
    @Override
    public String toString() {    	
    	return firstName+" "+lastName;
    }
    
    @Override
    public int hashCode() {    	
    	return super.hashCode();
    }
    
   }


package com.gsgtech.demo.employee.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author Ildemaro Medina
 * @author Jorge Guerrero
 * 
 */
@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1290841368344937836L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, unique = true, nullable = false)
	private Integer id;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "birth_date", nullable = true)
	private LocalDate birthDate;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(name = "num_phone", nullable = false, length = 15)
	private String numPhone;

	public Employee() {

	}	

	public Employee(String firstName, String lastName, LocalDate birthDate, String email, String numPhone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.numPhone = numPhone;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
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

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthdate) {
		this.birthDate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumPhone() {
		return numPhone;
	}

	public void setNumPhone(String numPhone) {
		this.numPhone = numPhone;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}

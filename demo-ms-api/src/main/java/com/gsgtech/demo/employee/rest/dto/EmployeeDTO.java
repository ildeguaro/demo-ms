package com.gsgtech.demo.employee.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Ildemaro Medina
 * @author Jorge Guerrero
 * 
 */
public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = -8959163451473550625L;

	private Integer id;

	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 50)
	private String lastName;

	private LocalDate birthDate;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
	private String numPhone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EmployeeDTO objDTO = (EmployeeDTO) o;
		if (!Objects.equals(id, objDTO.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDTO [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", email=");
		builder.append(email);
		builder.append(", numPhone=");
		builder.append(numPhone);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

}

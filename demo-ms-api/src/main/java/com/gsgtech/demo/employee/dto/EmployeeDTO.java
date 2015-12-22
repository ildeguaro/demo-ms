package com.gsgtech.demo.employee.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
*
* @author Ildemaro Medina
* 
*/
public class EmployeeDTO implements Serializable{
	
	
	private static final long serialVersionUID = -8959163451473550625L;

	private Integer id;
	
	@NotEmpty(message="First Name debe ser llenado")
    @Length(max=30, message="First Name muy largo. Valor Maximo: 30 Caracteres")
	private String  firstName;
	
	@NotEmpty(message="Last Name debe ser llenado")
	@Length(max=30, message="Last Name muy largo. Valor Maximo: 30 Caracteres")
	private String  lastName;
	
	
	//Desconocimiento de la validacion... siempre dio error
	private Date    birthDate;
	
	@NotEmpty(message="Email debe ser llenado")
	@Email
	private String  email;
	
	@NotEmpty(message="Number Phone debe ser llenado")
	@Pattern(regexp="^\\+(?:[0-9] ?){6,14}[0-9]$",message="Indique Number Phone")
	private String  numPhone;
	
	public EmployeeDTO(){
		
	}
	
	public EmployeeDTO(String firstName, String lastName, Date birthDate, String email, String numPhone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.numPhone = numPhone;
	}
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", email=" + email + ", numPhone=" + numPhone + "]";
	}
	
	

}

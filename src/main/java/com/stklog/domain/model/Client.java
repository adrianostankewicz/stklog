package com.stklog.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.stklog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
public class Client {

	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank
	@Column(name="last_name")
	private String lastName;
	
	@NotBlank
	private String doc;
	
	@NotBlank
	@Email
	private String email;
	
	
	private String cellphone;
	
	@NotBlank
	@Column(name="birth_date")
	private OffsetDateTime birthDate;
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
}

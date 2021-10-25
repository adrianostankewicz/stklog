package com.stklog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recepient {

	@NotBlank
	@Column(name = "recepient_name")
	private String name;
	
	@NotBlank
	@Column(name = "recepient_public_place")
	private String publicPlace;
	
	@NotBlank
	@Column(name = "recepient_number")
	private String number;
	
	@NotBlank
	@Column(name = "recepient_complement")
	private String complement;
	
	@NotBlank
	@Column(name = "recepient_district")
	private String district;
}

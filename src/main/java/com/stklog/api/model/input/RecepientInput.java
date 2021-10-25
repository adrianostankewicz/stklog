package com.stklog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecepientInput {

	@NotBlank
	private String name;
	
	@NotBlank
	private String publicPlace;
	
	@NotBlank
	private String number;
	
	@NotBlank
	private String complement;
	
	@NotBlank
	private String district;
}

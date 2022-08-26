package com.RESTAPI.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be of min 4 characters")
	private String name;

	@NotEmpty(message = "Email should not be left blank")
	@Email(message = "Email is not valid")
	private String email;

	@NotEmpty
	@Size(min = 6, message = "Password should have minimum 6 characters")
	private String password;

	@NotEmpty(message = "About should not be left blank")
	private String about;


}

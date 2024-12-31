package com.interland.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@NotBlank(message = "UserName can't be empty")
	private String userName;
	@NotBlank(message = "Password Can't be emply")
	private String password;

}

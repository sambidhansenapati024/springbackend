package com.interland.review.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.EmbeddedId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDto {
	
	private Integer id;
	 @Pattern(regexp = "^@[A-Za-z]{2,10}$", message = "Username must start with '@' followed by 2 to 10 alphabetic characters.")
	 private String userName;
	@NotBlank(message = "Mode of payment Can't be empty")
	private String modeOfPayment;
	 @Pattern(regexp = "^[A-Za-z]{2,20}$", message = "Your name must start by 2 to 10 alphabetic characters.")
	@NotBlank(message = "Please provide your name")
	private String name;
	@NotBlank(message = "Email can't be emply")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please provide a valid email address.")
	private String email;
	@NotBlank(message = "Platform can't be empty")
	private String platform;
	
	 @Range(min = (long) 1.0, max = (long) 10000000000.0,message = "price can't be 0")
	private Double price;
	 @NotBlank(message = "Please provide Your Duration")
	private String duration;

	private String status;
	@FutureOrPresent
	private LocalDate subscribedDate;
	@FutureOrPresent
	private LocalDate endSubscribtion;

}

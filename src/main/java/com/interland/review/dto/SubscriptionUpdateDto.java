package com.interland.review.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionUpdateDto {
	@NotNull
	private Integer id;
	@NotBlank(message = "UserName Can't be empty")
	 @Pattern(regexp = "^@[A-Za-z]{2,10}$", message = "Username must start with '@' followed by 2 to 10 alphabetic characters.")
	private String userName;
	@NotBlank(message = "Mode of payment Can't be empty")
	private String modeOfPayment;
	@NotBlank(message = "Please Provide Your Full Name")
	 @Pattern(regexp = "^[A-Za-z]{2,10}$", message = "Username must start by 2 to 10 alphabetic characters.")
	private String name;
	@NotBlank(message = "Email can't be emply")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please provide a valid email address.")
	private String email;
	@NotBlank(message = "Platform can't be empty")
	private String platform;
	
	 @Range(min = (long) 1.0, max = (long) 10000000000.0,message = "price can't be 0")
	private Double price;
	 @NotNull(message = "Please provide Your Duration")
	private String duration;
	@NotNull(message = "Status can't be emply")
	private String status;
	@PastOrPresent
	private LocalDate subscribedDate;
	@PastOrPresent
	private LocalDate endSubscribtion;
}

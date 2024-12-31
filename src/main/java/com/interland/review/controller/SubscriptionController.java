package com.interland.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.ServiceResponses;
import com.interland.review.dto.SubscriptionDto;
import com.interland.review.dto.SubscriptionUpdateDto;
import com.interland.review.dto.User;
import com.interland.review.exception.NoSubscriptionPresentException;
import com.interland.review.exception.NoUserPresentException;
import com.interland.review.exception.SubscriptionAlreadyActiveException;
import com.interland.review.service.SubscriptionServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sub-scribe")
@CrossOrigin(origins = "http://localhost:2726")
@RequiredArgsConstructor
public class SubscriptionController {
	private final SubscriptionServiceImpl service;
	
	@PostMapping
	public ResponseEntity<ServiceResponse> addSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) throws SubscriptionAlreadyActiveException{
		return new ResponseEntity<>(service.addSubscription(subscriptionDto),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ServiceResponse> updateSubscription(@Valid @RequestBody SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException{
		return new ResponseEntity<>(service.updateSubscription(subscriptionUpdateDto),HttpStatus.OK);
	}
	@PutMapping("/update-status")
	public ResponseEntity<ServiceResponse> updateSubscriptionStatus(@RequestBody SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException{
		return new ResponseEntity<>(service.updateSubscriptionStatus(subscriptionUpdateDto),HttpStatus.OK);
	}
	
	@GetMapping("/{id}/{userName}/{modeOfPayment}")
	public ResponseEntity<ServiceResponses> addSubscription(@PathVariable Integer id,@PathVariable String userName,@PathVariable String modeOfPayment) throws NoSubscriptionPresentException{
		return new ResponseEntity<>(service.getSubDetailsById(id, userName, modeOfPayment),HttpStatus.OK);
	}
	@GetMapping("/get-sub-platform")
	public ResponseEntity<ServiceResponses> getAllPlatforms() {
		return new ResponseEntity<>(service.getAllPlatform(),HttpStatus.OK);
	}
	
	@GetMapping("/get-sub-duration")
	public ResponseEntity<ServiceResponses> getDurationDetails() {
		return new ResponseEntity<>(service.getAllDurationDetails(),HttpStatus.OK);
	}
	 @GetMapping("/search")
		
		public ResponseEntity<ServiceResponse> search(@RequestParam("searchParam") String searchJsonString,
				@RequestParam("iDisplayStart") Integer pageStart, @RequestParam("iDisplayLength") Integer pageSize) {
			return new ResponseEntity<>(service.searchSubscription(searchJsonString, pageSize, pageStart), HttpStatus.OK);
		}
	
	
	
	

}

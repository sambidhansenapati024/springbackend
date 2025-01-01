package com.interland.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.User;
import com.interland.review.exception.NoUserPresentException;
import com.interland.review.service.AuthServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sub-scribe")
@CrossOrigin(origins = "https://subscription-management-zdl4.onrender.com")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthServiceImpl serviceImpl;
	
	
	 @PostMapping("/getaccesstoken")
		public ResponseEntity<ServiceResponse> getToken(@RequestBody User user) throws NoUserPresentException {
			return new ResponseEntity<>(serviceImpl.getAccessToken(user), HttpStatus.OK);
		}

}

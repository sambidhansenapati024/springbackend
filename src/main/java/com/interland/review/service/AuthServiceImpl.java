package com.interland.review.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.User;
import com.interland.review.entity.UserCredential;
import com.interland.review.exception.NoUserPresentException;
import com.interland.review.repo.UserRepo;
import com.interland.review.utils.AppConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepo userRepo;
	private final MessageSource messageSource;
	private final RestTemplate restTemplate;

	
	@Value("${oauth.token-url}")
	private String tokenUrl;
	@Value("${oauth.client-id}")
	private String clientId;
	@Value("${oauth.client-secret}")
	private String clientSecret;
	@Value("${oauth.grant-type}")
	private String grantType;

	
	
	public ServiceResponse getAccessToken(User user) throws NoUserPresentException {
		log.info(messageSource
				.getMessage("interland.project.product.details.ss14", null, LocaleContextHolder.getLocale()));
		Optional<UserCredential> users = userRepo.findById(user.getUserName());
		

	    if (!users.isPresent()) {
	    	log.error("Error");
	        throw new NoUserPresentException();
	    }

	    try {
	      
	        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
	        formData.add(AppConstants.GRANT_TYPE, grantType);
	        formData.add(AppConstants.CLIENT_ID, clientId);
	        formData.add(AppConstants.UERNAME, user.getUserName());
	        formData.add(AppConstants.PASSWORD, user.getPassword());
	        formData.add(AppConstants.CLIENT_SECRET, clientSecret);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
	        JSONObject tokenResponse = restTemplate.exchange(tokenUrl, HttpMethod.POST, requestEntity, JSONObject.class).getBody();
	        return new ServiceResponse(AppConstants.SUCCESS,  messageSource
					.getMessage("interland.project.product.details.ss9", null, LocaleContextHolder.getLocale()), 
                    List.of(tokenResponse));
	        
	    } 
	    catch (HttpClientErrorException e) {
	        log.error("Error occurred: " + e.getMessage());
	        throw e;

	    }
	    catch (Exception e) {
	        log.error("Error occurred: " + e.getMessage());
	        
	    }
	    return new ServiceResponse(AppConstants.FAILED,messageSource
				.getMessage("interland.project.product.details.ss19", null, LocaleContextHolder.getLocale()), null);
	}

}

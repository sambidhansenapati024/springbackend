package com.interland.review.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.interland.review.exception.NoSubscriptionPresentException;
import com.interland.review.exception.NoUserPresentException;
import com.interland.review.exception.SubscriptionAlreadyActiveException;
import com.interland.review.exception.SubscriptionAlredyCanceledException;
import com.interland.review.utils.AppConstants;

import lombok.RequiredArgsConstructor;



@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
class ExceptionControllerAdvice {
	private final MessageSource messageSource;

	private static Logger logger = LogManager.getLogger(ExceptionControllerAdvice.class);

	@SuppressWarnings("unchecked")
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<JSONObject> handleValidationExceptions(MethodArgumentNotValidException ex) {
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			JSONObject detail = new JSONObject();
			try {
				detail.put(((FieldError) error).getField(), error.getDefaultMessage());
				details.add(detail);
			} catch (Exception e) {
				logger.error(e);
			}
		});
		response.put(AppConstants.CODE, AppConstants.VALERRCOD);
		response.put(AppConstants.MESSAGE, messageSource
				.getMessage("interland.project.product.details.ss26", null, LocaleContextHolder.getLocale()));
		response.put(AppConstants.DETAILS, details);

		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	@SuppressWarnings("unchecked")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<JSONObject> handleValidationExceptions(HttpClientErrorException ex) {
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();
		
		response.put(AppConstants.CODE, AppConstants.VALERRCOD);
			response.put(AppConstants.MESSAGE, messageSource
					.getMessage("interland.project.product.details.ss25", null, LocaleContextHolder.getLocale()));
			response.put(AppConstants.DETAILS, details);
			response.put("access_token","No token");
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
		}
		
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<JSONObject> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();
		response.put(AppConstants.CODE, AppConstants.NULLCOD);
		response.put(AppConstants.MESSAGE, messageSource
				.getMessage("interland.project.product.details.ss27", null, LocaleContextHolder.getLocale()));
		response.put(AppConstants.DETAILS, details);
		response.put("access_token",messageSource
				.getMessage("interland.project.product.details.ss24", null, LocaleContextHolder.getLocale()));
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	@SuppressWarnings("unchecked")
	@ExceptionHandler(NoSubscriptionPresentException.class)
	public ResponseEntity<JSONObject> handleNoSubscriptionPresentException(NoSubscriptionPresentException ex){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();		
			response.put(AppConstants.CODE,AppConstants.FAILED);
			response.put(AppConstants.MESSAGE, messageSource
					.getMessage("interland.project.product.details.ss23", null, LocaleContextHolder.getLocale())); 
			response.put(AppConstants.DETAILS, details);
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(NoUserPresentException.class)
	public ResponseEntity<JSONObject> handleNoUserPresentException(NoUserPresentException ex){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();		
			response.put(AppConstants.CODE,AppConstants.FAILED);
			response.put(AppConstants.MESSAGE, messageSource
					.getMessage("interland.project.product.details.ss22", null, LocaleContextHolder.getLocale())); 
			response.put(AppConstants.DETAILS, details);
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(SubscriptionAlreadyActiveException.class)
	public ResponseEntity<JSONObject> handleNoSubscriptionPresentException(SubscriptionAlreadyActiveException ex){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();		
			response.put(AppConstants.CODE,AppConstants.FAILED);
			response.put(AppConstants.MESSAGE, messageSource
					.getMessage("interland.project.product.details.ss21", null, LocaleContextHolder.getLocale())); 
			response.put(AppConstants.DETAILS, details);
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(SubscriptionAlredyCanceledException.class)
	public ResponseEntity<JSONObject> handleNoSubscriptionPresentException(SubscriptionAlredyCanceledException ex){
		JSONObject response = new JSONObject();
		JSONArray details = new JSONArray();		
			response.put(AppConstants.CODE,AppConstants.FAILED);
			response.put(AppConstants.MESSAGE, messageSource
					.getMessage("interland.project.product.details.ss20", null, LocaleContextHolder.getLocale())); 
			response.put(AppConstants.DETAILS, details);
			return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

}

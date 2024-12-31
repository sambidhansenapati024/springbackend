package com.interland.review.service;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.ServiceResponses;
import com.interland.review.dto.SubscriptionDto;
import com.interland.review.dto.SubscriptionUpdateDto;
import com.interland.review.exception.NoSubscriptionPresentException;
import com.interland.review.exception.SubscriptionAlreadyActiveException;

public interface SubscriptionService {
	
	ServiceResponse addSubscription(SubscriptionDto subscriptionDto) throws SubscriptionAlreadyActiveException;
	
	ServiceResponse updateSubscription(SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException;
	ServiceResponse updateSubscriptionStatus(SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException;
	ServiceResponses getSubDetailsById(Integer id,String userName,String modeOfPayment) throws NoSubscriptionPresentException;
	ServiceResponses getAllPlatform();
	ServiceResponses getAllDurationDetails();

	ServiceResponse searchSubscription(String searchDataJson, Integer pageSize, Integer pageStart);

}

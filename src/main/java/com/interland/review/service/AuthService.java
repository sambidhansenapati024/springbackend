package com.interland.review.service;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.User;
import com.interland.review.exception.NoUserPresentException;

public interface AuthService {
	ServiceResponse getAccessToken(User user) throws NoUserPresentException;

}

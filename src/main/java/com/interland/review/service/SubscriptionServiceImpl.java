package com.interland.review.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.interland.review.dto.ServiceResponse;
import com.interland.review.dto.ServiceResponses;
import com.interland.review.dto.SubscriptionDto;
import com.interland.review.dto.SubscriptionUpdateDto;
import com.interland.review.entity.Duration;
import com.interland.review.entity.SubsPlatform;
import com.interland.review.entity.SubscripsionPk;
import com.interland.review.entity.Subscription;
import com.interland.review.exception.NoSubscriptionPresentException;
import com.interland.review.exception.SubscriptionAlreadyActiveException;
import com.interland.review.exception.SubscriptionAlredyCanceledException;
import com.interland.review.repo.DurationRepo;
import com.interland.review.repo.SubPlatformRepo;
import com.interland.review.repo.SubscriptionRepo;
import com.interland.review.repo.spec.SubscriptionSpecification;
import com.interland.review.utils.AppConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
	
	private final SubscriptionRepo subscriptionRepo;
	private final MessageSource messageSource;
	private final SubPlatformRepo platformRepo;
	private final DurationRepo durationRepo;

	@Override
	public ServiceResponse addSubscription(SubscriptionDto subscriptionDto) throws SubscriptionAlreadyActiveException {
		
		if(CheckAvaibilityOfSubscription(subscriptionDto)) {
			throw new SubscriptionAlreadyActiveException();	
		}
		
		try {
			Subscription subscription=createSubscription(subscriptionDto);
			subscriptionRepo.save(subscription);
			return new ServiceResponse(AppConstants.SUCCESS, messageSource
					.getMessage("interland.project.product.details.ss1", null, LocaleContextHolder.getLocale()), List.of());
			
		}catch (Exception e) {
			log.error("error while adding data");
			
		}
		return new ServiceResponse(AppConstants.FAILED, messageSource
				.getMessage("interland.project.product.details.ss2", null, LocaleContextHolder.getLocale()), List.of());
	}

	@Override
	public ServiceResponse updateSubscription(SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException {


	try {
		Subscription existingSubscription=CheckexistingSubsUpdate(subscriptionUpdateDto);
		Subscription subscription=Subscription.builder()
				.pk(existingSubscription.getPk())
				.name(subscriptionUpdateDto.getName())
				.email(subscriptionUpdateDto.getEmail())
				.platform(subscriptionUpdateDto.getPlatform())
				.price(subscriptionUpdateDto.getPrice())
				.duration(subscriptionUpdateDto.getDuration())
				.status(subscriptionUpdateDto.getStatus())
				.subscribedDate(subscriptionUpdateDto.getSubscribedDate())
				.endSubscribtion(subscriptionUpdateDto.getEndSubscribtion())
				.build();
		
		subscriptionRepo.save(subscription);
		return new ServiceResponse(AppConstants.SUCCESS, messageSource
				.getMessage("interland.project.product.details.ss4", null, LocaleContextHolder.getLocale()), List.of());
		
	}catch (Exception e) {
		log.error("Error while updating");
		
	}
	return new ServiceResponse(AppConstants.FAILED, messageSource
			.getMessage("interland.project.product.details.ss5", null, LocaleContextHolder.getLocale()), List.of());
	}

	@Override
	public ServiceResponse updateSubscriptionStatus(SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException {

		
		try {
			Subscription existingSubscription=CheckexistingSubsUpdate(subscriptionUpdateDto);
			Subscription subscription=Subscription.builder()
					.pk(existingSubscription.getPk())
					.name(subscriptionUpdateDto.getName())
					.email(subscriptionUpdateDto.getEmail())
					.platform(subscriptionUpdateDto.getPlatform())
					.price(subscriptionUpdateDto.getPrice())
					.duration(subscriptionUpdateDto.getDuration())
					.status(AppConstants.UNSUBSCRIBED)
					.subscribedDate(subscriptionUpdateDto.getSubscribedDate())
					.endSubscribtion(LocalDate.now())
					.build();
			
			subscriptionRepo.save(subscription);
			return new ServiceResponse(AppConstants.SUCCESS, messageSource
					.getMessage("interland.project.product.details.ss7", null, LocaleContextHolder.getLocale()), List.of());

		}catch (Exception e) {
			log.error("Error while updating the status");
			
		}
		return new ServiceResponse(AppConstants.FAILED, messageSource
				.getMessage("interland.project.product.details.ss8", null, LocaleContextHolder.getLocale()), List.of());
		
	}

	@Override
	public ServiceResponses getSubDetailsById(Integer id, String userName, String modeOfPayment) throws NoSubscriptionPresentException {

		SubscripsionPk pk =setPkByIds(id, userName, modeOfPayment);
		Subscription getDetails = subscriptionRepo.findById(pk).orElseThrow(() -> new NoSubscriptionPresentException());
		try {
			
			SubscriptionDto subscription = SubscriptionDto.builder()
					.id(id)
					.userName(userName)
					.modeOfPayment(modeOfPayment)
					.name(getDetails.getName())
					.email(getDetails.getEmail())
					.platform(getDetails.getPlatform())
					.price(getDetails.getPrice())
					.duration(getDetails.getDuration())
					.status(getDetails.getStatus())
					.subscribedDate(getDetails.getSubscribedDate())
					.endSubscribtion(getDetails.getEndSubscribtion())
					.build();
			return new ServiceResponses(AppConstants.SUCCESS,messageSource
					.getMessage("interland.project.product.details.ss12", null, LocaleContextHolder.getLocale()), List.of(subscription));
			
		}catch (Exception e) {
			
		}
		return new ServiceResponses(AppConstants.FAILED,messageSource
				.getMessage("interland.project.product.details.ss13", null, LocaleContextHolder.getLocale()), List.of());
	}

	
	@Override
	public ServiceResponses getAllPlatform() {
	try {
		List<SubsPlatform> platforms = platformRepo.findAll();
		return new ServiceResponses(AppConstants.SUCCESS,messageSource
				.getMessage("interland.project.product.details.ss15", null, LocaleContextHolder.getLocale()), List.of(platforms));
	} catch (NoSuchMessageException e) {
		
		e.printStackTrace();
	}
	return new ServiceResponses(AppConstants.FAILED,messageSource
			.getMessage("interland.project.product.details.ss16", null, LocaleContextHolder.getLocale()), List.of());
	}

	
	@Override
	public ServiceResponses getAllDurationDetails() {
		try {
			List<Duration> getAllDuration=durationRepo.findAll();
			return new ServiceResponses(AppConstants.SUCCESS,messageSource
					.getMessage("interland.project.product.details.ss17", null, LocaleContextHolder.getLocale()), List.of(getAllDuration));
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new ServiceResponses(AppConstants.FAILED,messageSource
				.getMessage("interland.project.product.details.ss18", null, LocaleContextHolder.getLocale()), List.of());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceResponse searchSubscription(String searchDataJson, Integer pageSize, Integer pageStart) {
		
		JSONObject detail = new JSONObject();
		try {
			Pageable pageable = PageRequest.of(pageStart, pageSize);
			Specification<Subscription> spec = Specification.where(SubscriptionSpecification.searchSubs(searchDataJson));
			Page<Subscription> page = subscriptionRepo.findAll(spec, pageable);
			Long count = subscriptionRepo.count(SubscriptionSpecification.searchSubs(searchDataJson));
			
			JSONArray subsList = mapProductsToJson(page);
			
			detail.put(AppConstants.TOTAL_RECORD, count);
			detail.put(AppConstants.TOTAL_DISPLAY_RECORD, count);
			detail.put(AppConstants.AA_DATA, subsList);
			return new ServiceResponse(AppConstants.SUCCESS,messageSource
					.getMessage("interland.project.product.details.ss10", null, LocaleContextHolder.getLocale()), List.of(detail));
		} catch (Exception e) {
			log.error("Error Occured " + e.getMessage());
			detail.put(AppConstants.TOTAL_RECORD, 0);
			detail.put(AppConstants.TOTAL_DISPLAY_RECORD, 0);
			detail.put(AppConstants.AA_DATA, new JSONArray());
			
		}
		return new ServiceResponse(AppConstants.FAILED,messageSource
				.getMessage("interland.project.product.details.ss11", null, LocaleContextHolder.getLocale()), List.of());
		
	}
		
	@SuppressWarnings("unchecked")
	private JSONArray mapProductsToJson(Page<Subscription> page) {
		JSONArray productList = new JSONArray();
	    for (Subscription subscription : page) {
	        JSONObject productDetails = new JSONObject();
	        productDetails.put("id", subscription.getPk().getId());
	        productDetails.put("userName", subscription.getPk().getUserName());
	        productDetails.put("modeOfPayment", subscription.getPk().getModeOfPayment());
	        productDetails.put("name", subscription.getName());
	        productDetails.put("email", subscription.getEmail());
	        productDetails.put("platform", subscription.getPlatform());
	        productDetails.put("price", subscription.getPrice());
	        productDetails.put("duration", subscription.getDuration());
	        productDetails.put("subscribedDate", subscription.getSubscribedDate());
	        productDetails.put("endSubscribtion", subscription.getEndSubscribtion());
	        productDetails.put("status", subscription.getStatus());

	        productList.add(productDetails);
	    }
	    return productList;
	}
	
	
	private SubscripsionPk setPk(SubscriptionDto subscriptionDto) {
		return SubscripsionPk.builder()
				.id(subscriptionDto.getId())
				.modeOfPayment(subscriptionDto.getModeOfPayment())
				.userName(subscriptionDto.getUserName())
				.build();
	}
	
	private SubscripsionPk setPkForUpdate(SubscriptionUpdateDto subscriptionUpdateDto) {
		return SubscripsionPk.builder()
				.id(subscriptionUpdateDto.getId())
				.modeOfPayment(subscriptionUpdateDto.getModeOfPayment())
				.userName(subscriptionUpdateDto.getUserName())
				.build();
	}
	
	private Boolean CheckAvaibilityOfSubscription(SubscriptionDto subscriptionDto) {
		SubscripsionPk pk=setPk(subscriptionDto);
		Optional<Subscription> getDetails=subscriptionRepo.findById(pk);
		return getDetails.isPresent();
	}
	
	private Subscription CheckexistingSubsUpdate(SubscriptionUpdateDto subscriptionUpdateDto) throws NoSubscriptionPresentException, SubscriptionAlredyCanceledException {
		SubscripsionPk pk = setPkForUpdate(subscriptionUpdateDto);
		Optional<Subscription> getDetails = subscriptionRepo.findById(pk);
		if (!getDetails.isPresent()) {
			throw new NoSubscriptionPresentException();
		}
		 Subscription existingProduct = getDetails.get();
		    if (existingProduct.getStatus().equals(AppConstants.UNSUBSCRIBED)) {
		       throw new SubscriptionAlredyCanceledException();
		    }
		return existingProduct;
	}
	
	private Subscription createSubscription(SubscriptionDto dto) {
		SubscripsionPk pk = setPk(dto);
		
		return Subscription.builder()
				.pk(pk)
				.name(dto.getName())
				.email(dto.getEmail())
				.platform(dto.getPlatform())
				.price(dto.getPrice())
				.duration(dto.getDuration())
				.status(AppConstants.SUBSCRIBED)
				.subscribedDate(LocalDate.now())
				.endSubscribtion(null)
				.build();
	}
	
	private SubscripsionPk setPkByIds(Integer id,String userName,String modeOfPayment) {
		return SubscripsionPk.builder()
				.id(id)
				.userName(userName)
				.modeOfPayment(modeOfPayment)
				.build();
	}

}

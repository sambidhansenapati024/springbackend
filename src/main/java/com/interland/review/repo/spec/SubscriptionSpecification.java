package com.interland.review.repo.spec;


import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interland.review.entity.Subscription;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SubscriptionSpecification {
	public static Specification<Subscription> searchSubs(String searchParam) {
		return new Specification<Subscription>() {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<Subscription> root, CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = null;
				String userNames = "";
				String platformName="";
				String status = "";
				Integer id=null;
				String duration="";
				String modeOfPayment="";
				String email="";
				String fromDate = "";
                String toDate = "";
                String date="";
				try {
					if (StringUtils.hasLength(searchParam)) {
						JSONObject searchParamJson = new ObjectMapper().readValue(searchParam, JSONObject.class);
						userNames = (String) searchParamJson.get("userName");
						status = (String) searchParamJson.get("status");
						id = searchParamJson.get("id") != null
								? Integer.parseInt(searchParamJson.get("id").toString())
								: null;
						platformName = (String) searchParamJson.get("platform");
						modeOfPayment = (String) searchParamJson.get("modeOfPayment");
						email = (String) searchParamJson.get("email");
					    fromDate = (String) searchParamJson.get("fromDate");
                        toDate = (String) searchParamJson.get("toDate");
                        duration = (String) searchParamJson.get("duration");
                        date=(String) searchParamJson.get("subscribedDate");
					}

					if (StringUtils.hasLength(userNames)) {
						Predicate namePredicate = criteriaBuilder.equal(root.get("pk").get("userName"), userNames);
						if (finalPredicate != null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate);
						} else {
							finalPredicate = criteriaBuilder.and(namePredicate);
						}
					}
					if (StringUtils.hasLength(email)) {
					    Predicate orderPredicate = criteriaBuilder.equal(root.get("email"), email);
					    if (finalPredicate != null) {
					        finalPredicate = criteriaBuilder.and(finalPredicate, orderPredicate);
					    } else {
					        finalPredicate = criteriaBuilder.and(orderPredicate);
					    }
					}
					
					if (StringUtils.hasLength(modeOfPayment)) {
					    Predicate orderPredicate = criteriaBuilder.equal(root.get("pk").get("modeOfPayment"), modeOfPayment);
					    if (finalPredicate != null) {
					        finalPredicate = criteriaBuilder.and(finalPredicate, orderPredicate);
					    } else {
					        finalPredicate = criteriaBuilder.and(orderPredicate);
					    }
					}


					if (StringUtils.hasLength(status)) {
						Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
						if (finalPredicate != null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, statusPredicate);
						} else {
							finalPredicate = criteriaBuilder.and(statusPredicate);
						}
					}
					if (StringUtils.hasLength(duration)) {
						Predicate platformNamePredicate = criteriaBuilder.equal(root.get("duration"), duration);
						if (finalPredicate != null) {
							finalPredicate = criteriaBuilder.and(finalPredicate,platformNamePredicate);
						} else {
							finalPredicate = criteriaBuilder.and(platformNamePredicate);
						}
					}
					if (StringUtils.hasLength(date)) {
						LocalDate start = LocalDate.parse(date);
						Predicate datePredicate = criteriaBuilder.equal(root.get("subscribedDate"), date);
						if (finalPredicate != null) {
							finalPredicate = criteriaBuilder.and(finalPredicate,datePredicate);
						} else {
							finalPredicate = criteriaBuilder.and(datePredicate);
						}
					}
					if (id!=null) {
						Predicate idProofNumberPredicate = criteriaBuilder.equal(root.get("pk").get("id"), id);
						if (finalPredicate != null) {
							finalPredicate = criteriaBuilder.and(finalPredicate, idProofNumberPredicate);
						} else {
							finalPredicate = criteriaBuilder.and(idProofNumberPredicate);
						}
					}
					
					if (StringUtils.hasLength(fromDate) && StringUtils.hasLength(toDate)) {
					    
					        LocalDate start = LocalDate.parse(fromDate);
					        LocalDate end = LocalDate.parse(toDate);
					        Predicate datePredicate = criteriaBuilder.between(root.get("subscribedDate"), start, end);
					        if (finalPredicate != null) {
					            finalPredicate = criteriaBuilder.and(finalPredicate, datePredicate);
					        } else {
					            finalPredicate = datePredicate;
					        }
					
					}
				} catch (Exception e) {

				}
				return finalPredicate;
			}			
		};
	}
}


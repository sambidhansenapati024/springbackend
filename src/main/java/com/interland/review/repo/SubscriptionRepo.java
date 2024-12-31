package com.interland.review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.interland.review.entity.SubscripsionPk;
import com.interland.review.entity.Subscription;
@Service
public interface SubscriptionRepo extends JpaRepository<Subscription, SubscripsionPk>,JpaSpecificationExecutor<Subscription> {

}

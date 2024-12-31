package com.interland.review.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="subscription")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subscription {
	
	@Id
	@EmbeddedId
	private SubscripsionPk pk;
	@Column(name="Name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="platform")
	private String platform;
	@Column(name="price")
	private Double price;
	@Column(name="duration")
	private String duration;
	@Column(name="status")
	private String status;
	@Column(name="subscribedate")
	private LocalDate subscribedDate;
	@Column(name="endsubscribedate")
	private LocalDate endSubscribtion;
	
	

}

package com.interland.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Duration")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Duration {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="duration")
	private String duration;
	

}

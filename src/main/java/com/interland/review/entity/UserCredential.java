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
@Table(name="subscuser")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredential {
	@Id
	@Column(name="username")
	private String userName;
	

}

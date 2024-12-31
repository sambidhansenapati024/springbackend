package com.interland.review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interland.review.entity.UserCredential;
@Repository
public interface UserRepo extends JpaRepository<UserCredential, String> {

}

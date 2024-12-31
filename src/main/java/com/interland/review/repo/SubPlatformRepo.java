package com.interland.review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interland.review.entity.SubsPlatform;
@Repository
public interface SubPlatformRepo extends JpaRepository<SubsPlatform, Integer> {

}

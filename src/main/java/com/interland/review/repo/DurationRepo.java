package com.interland.review.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interland.review.entity.Duration;

@Repository
public interface DurationRepo extends JpaRepository<Duration, Integer> {

}

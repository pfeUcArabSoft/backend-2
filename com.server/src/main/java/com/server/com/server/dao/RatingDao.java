package com.server.com.server.dao;

import com.server.com.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingDao extends JpaRepository<Rating, Long> {
}

package com.server.com.server.dao;

import com.server.com.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingDao extends JpaRepository<Rating, Long> {
    @Query(value = "SELECT * FROM rating r JOIN user u ON r.user_id = u.user_name WHERE u.user_name = :userName", nativeQuery = true)
    List<Rating> findRatingsByUserName(@Param("userName") String userName);

}

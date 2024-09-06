package com.server.com.server.controller;

import com.server.com.server.dao.RatingDao;
import com.server.com.server.entity.Rating;
import com.server.com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingDao ratingDao;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating savedRating = ratingDao.save(rating);
        return ResponseEntity.ok(savedRating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingDao.findAll();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        Optional<Rating> rating = ratingDao.findById(id);
        if (rating.isPresent()) {
            return ResponseEntity.ok(rating.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating updatedRating) {
        Optional<Rating> existingRating = ratingDao.findById(id);
        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();
            rating.setScore(updatedRating.getScore());
            rating.setComment(updatedRating.getComment());
            Rating savedRating = ratingDao.save(rating);
            return ResponseEntity.ok(savedRating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        Optional<Rating> rating = ratingDao.findById(id);
        if (rating.isPresent()) {
            ratingDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<Rating>> getRatingsByUserName(@PathVariable String userName) {
        List<Rating> ratings = ratingDao.findRatingsByUserName(userName);
        return ResponseEntity.ok(ratings);
    }
}

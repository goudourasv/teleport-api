package com.goudourasv.domain.ratings;

import com.goudourasv.data.ratings.RatingsRepository;
import com.goudourasv.http.courses.dto.RatingCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class RatingsService {
    private final RatingsRepository ratingsRepository;

    @Inject
    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Transactional
    public Rating createRating(UUID courseId, UUID userId, RatingCreate ratingCreate) {
        Rating rating = ratingsRepository.createRating(courseId, userId, ratingCreate);
        return rating;
    }
}

package com.abhishek.aiservice.repository;

import com.abhishek.aiservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    List<Recommendation> findByUserId(String userId); //JPA handles this

    Optional<Recommendation> findByActivityId(String activityId); //JPA handles this
}

package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {
    Recommendation findRecommendationByName(String name);
}

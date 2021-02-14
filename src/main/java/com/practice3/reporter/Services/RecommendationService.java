package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Consultant;
import com.practice3.reporter.Entities.Recommendation;
import com.practice3.reporter.Repositories.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    private RecommendationRepository repository;

    @Autowired
    public void setRepository(RecommendationRepository repository) {
        this.repository = repository;
    }

    public void save(Recommendation recommendation) {
        repository.save(recommendation);
    }

    public List<Recommendation> getAll() {
        return repository.findAll();
    }

    public Recommendation getById(long id) {
        return repository.getOne(id);
    }

    public Recommendation getRecommendationByName(String name) {
        return repository.findRecommendationByName(name);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }

}

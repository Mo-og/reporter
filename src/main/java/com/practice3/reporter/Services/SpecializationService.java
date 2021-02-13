package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Specialization;
import com.practice3.reporter.Repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {
    private SpecializationRepository repository;

    @Autowired
    public void setRepository(SpecializationRepository repository) {
        this.repository = repository;
    }

    public void saveConsultant(Specialization specialization) {
        repository.save(specialization);
    }

    public List<Specialization> getAllConsultations() {
        return repository.findAll();
    }

    public Specialization getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }
}

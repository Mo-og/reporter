package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Consultation;
import com.practice3.reporter.Repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    private ConsultationRepository repository;

    @Autowired
    public void setRepository(ConsultationRepository repository) {
        this.repository = repository;
    }

    public void save(Consultation consultation) {
        repository.save(consultation);
    }

    public List<Consultation> getAll() {
        return repository.findAll();
    }

    public Consultation getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }
}
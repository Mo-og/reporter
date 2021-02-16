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

    public void save(Specialization specialization) {
        repository.save(specialization);
    }

    public List<Specialization> getAll() {
        return repository.findAll();
    }

    public Specialization getById(long id) {
        return repository.getOne(id);
    }

    public Specialization getSpecializationByName(String name) {
        return repository.findSpecializationBySpecializationName(name);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }

    public List<Specialization> getAllForDoctorId(long consultantId) {
        return repository.findAllByConsultants(consultantId);
    }

}

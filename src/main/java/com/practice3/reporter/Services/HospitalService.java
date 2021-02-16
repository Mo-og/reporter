package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Hospital;
import com.practice3.reporter.Repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    private HospitalRepository repository;

    @Autowired
    public void setRepository(HospitalRepository repository) {
        this.repository = repository;
    }

    public Hospital getByName(String name){
        return repository.findFirstByName(name);
    }

    public void save(Hospital specialization) {
        repository.save(specialization);
    }

    public List<Hospital> getAll() {
        return repository.findAll();
    }

    public Hospital getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }
}

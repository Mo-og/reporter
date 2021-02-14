package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Consultant;
import com.practice3.reporter.Repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantService {
    private ConsultantRepository repository;

    @Autowired
    public void setRepository(ConsultantRepository repository) {
        this.repository = repository;
    }

    public void save(Consultant consultant) {
        repository.save(consultant);
    }

    public List<Consultant> getAll() {
        return repository.findAll();
    }

    public Consultant getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }


    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }


}

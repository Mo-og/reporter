package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Transport;
import com.practice3.reporter.Repositories.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {
    private TransportRepository repository;

    @Autowired
    public void setRepository(TransportRepository repository) {
        this.repository = repository;
    }

    public void save(Transport specialization) {
        repository.save(specialization);
    }

    public List<Transport> getAll() {
        return repository.findAll();
    }

    public Transport getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }
}

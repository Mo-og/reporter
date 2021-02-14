package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Report;
import com.practice3.reporter.Entities.Transport;
import com.practice3.reporter.Repositories.ReportRepository;
import com.practice3.reporter.Repositories.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private ReportRepository repository;

    @Autowired
    public void setRepository(ReportRepository repository) {
        this.repository = repository;
    }

    public void save(Report specialization) {
        repository.save(specialization);
    }

    public List<Report> getAll() {
        return repository.findAll();
    }

    public Report getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }
}

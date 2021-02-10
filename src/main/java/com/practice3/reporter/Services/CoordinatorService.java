package com.practice3.reporter.Services;

import com.practice3.reporter.Entities.Consultant;
import com.practice3.reporter.Entities.Coordinator;
import com.practice3.reporter.Repositories.ConsultantRepository;
import com.practice3.reporter.Repositories.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {
    private CoordinatorRepository repository;

    @Autowired
    public void setRepository(CoordinatorRepository repository) {
        this.repository = repository;
    }

    public void saveCoordinator(Coordinator coordinator) {
        repository.save(coordinator);
    }

    public List<Coordinator> getAllCoordinators() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public Coordinator getById(long id) {
        return repository.getOne(id);
    }

    public void removeById(long id) {
        repository.deleteById(id);
    }

    public boolean existsWithId(long id) {
        return repository.existsById(id);
    }


    public void removeByFullName(String surname, String name, String patronymic) {
        Coordinator coordinator = repository.findFirstBySurnameAndNameAndPatronymic(surname, name, patronymic);
        if (coordinator != null)
            repository.delete(coordinator);
    }
}

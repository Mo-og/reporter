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

    public void save(Coordinator coordinator) {
        repository.save(coordinator);
    }

    public List<Coordinator> getAll() {
        return repository.findAll();
    }

    public Coordinator getById(long id) {
        return repository.getOne(id);
    }

    public Coordinator findByFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) return null;
        String surname, name, patronymic;
        fullName = fullName.trim();
        try {
            surname = fullName.substring(0, fullName.indexOf(' ')).trim();
            name = fullName.substring(fullName.indexOf(' ') + 1, fullName.lastIndexOf(' ')).trim();
            patronymic = fullName.substring(fullName.lastIndexOf(' ') + 1).trim();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Full name of coordinator is incorrect/has no whitespaces.");
            e.printStackTrace();
            return null;
        }
        //System.out.println(surname + " " + name + " " + patronymic);
        return repository.findFirstBySurnameAndNameAndPatronymic(surname, name, patronymic);
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

    public void remove(Coordinator coordinator) {
        repository.delete(coordinator);
    }
}

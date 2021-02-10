package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator,Long> {
    Coordinator findFirstBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);
}
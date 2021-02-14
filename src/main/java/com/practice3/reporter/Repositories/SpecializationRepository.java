package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
    Specialization findSpecializationByName(String name);
}

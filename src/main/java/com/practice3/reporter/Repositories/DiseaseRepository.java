package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease,Long> {
}

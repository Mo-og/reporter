package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}

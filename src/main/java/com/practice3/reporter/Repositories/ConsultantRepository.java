package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<Consultant,Long> {
    Consultant findConsultantByName(String name);
}
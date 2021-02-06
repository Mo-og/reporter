package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}

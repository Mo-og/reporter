package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Consultant;
import com.practice3.reporter.Entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findFirstBySpecializationName(String name);

    @Query(value = "SELECT * FROM specializations s " +
            "RIGHT JOIN consultants_specializations cs " +
            "On s.specialization_id = cs.specializations_specialization_id " +
            "Where cs.consultants_consultant_id = ?1 ",
            nativeQuery = true)
    List<Specialization> findAllByConsultantsd(Long id);
}

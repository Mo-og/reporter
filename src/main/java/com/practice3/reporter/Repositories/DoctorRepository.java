package com.practice3.reporter.Repositories;

import com.practice3.reporter.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
}

package com.practice3.reporter.Repositories;

import com.practice3.reporter.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findFirstByDate(Date date);
}

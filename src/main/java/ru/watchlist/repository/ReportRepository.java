package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.report.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

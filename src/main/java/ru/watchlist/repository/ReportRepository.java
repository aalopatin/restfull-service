package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

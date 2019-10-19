package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.report.TypeReport;

public interface TypeReportRepository extends JpaRepository<TypeReport, Long> {
}

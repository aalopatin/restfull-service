package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.TypeReport;

public interface TypeReportRepository extends JpaRepository<TypeReport, Long> {
}

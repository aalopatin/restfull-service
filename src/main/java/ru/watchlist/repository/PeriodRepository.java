package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.report.period.Period;

public interface PeriodRepository extends JpaRepository<Period, String> {

}

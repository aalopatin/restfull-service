package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.report.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}

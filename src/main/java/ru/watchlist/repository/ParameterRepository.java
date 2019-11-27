package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}

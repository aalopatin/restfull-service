package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.report.GroupParameters;

public interface GroupParametersRepository extends JpaRepository<GroupParameters, Long> {
}

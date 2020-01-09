package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.watchlist.domain.GroupParameters;
import ru.watchlist.domain.TypeReport;

import java.util.List;

public interface GroupParametersRepository extends JpaRepository<GroupParameters, Long>, JpaSpecificationExecutor<GroupParameters> {

    List<GroupParameters> findAllByTypeReport_Id(Long typeReportsId);

}

package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.watchlist.domain.GroupParameter;

import java.util.List;

public interface GroupParameterRepository extends JpaRepository<GroupParameter, Long>, JpaSpecificationExecutor<GroupParameter> {

    List<GroupParameter> findAllByTypeReport_Id(Long typeReportsId);

}

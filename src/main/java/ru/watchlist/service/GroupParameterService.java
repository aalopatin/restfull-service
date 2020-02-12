package ru.watchlist.service;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.GroupParameter;
import ru.watchlist.dto.groupparameter.GroupParameterIdDTO;
import ru.watchlist.mapper.GroupParameterMapper;
import ru.watchlist.repository.GroupParameterRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class GroupParameterService {

    @Autowired
    GroupParameterRepository groupParameterRepository;

    @Autowired
    GroupParameterMapper groupParameterMapper;

    //Repository
    @Named("findGroupById")
    public GroupParameter findById(Long id) throws EntityNotFoundException {
        if (id != null) {
            return groupParameterRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(GroupParameter.class, "id", id.toString()));
        } else {
            return null;
        }
    }

    public List<GroupParameter> findAll() {
        return groupParameterRepository.findAll();
    }

    public List<GroupParameter> findAll(Long typeReportId) {
        return groupParameterRepository.findAllByTypeReport_Id(typeReportId);
    }

    public GroupParameter createGroupParameter(GroupParameter groupParameters) {
        return groupParameterRepository.save(groupParameters);
    }

    public GroupParameter save(Long id, GroupParameterIdDTO groupParameterIdDTO) throws EntityNotFoundException {
        GroupParameter groupParameterFromDB = findById(id);
        groupParameterMapper.fill(groupParameterIdDTO, groupParameterFromDB);
        return groupParameterRepository.save(groupParameterFromDB);
    }

    public void deleteGroupParameter(Long id) throws EntityNotFoundException {
        try {
            groupParameterRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(GroupParameter.class, "id", id.toString());
        }
    }

}

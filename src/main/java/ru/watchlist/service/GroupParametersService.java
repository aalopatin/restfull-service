package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.GroupParameters;
import ru.watchlist.mapper.GroupParametersMapper;
import ru.watchlist.repository.GroupParametersRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class GroupParametersService {

    @Autowired
    GroupParametersRepository groupParametersRepository;

    @Autowired
    GroupParametersMapper groupParametersMapper;

    //Repository
    public GroupParameters findById(Long id) throws EntityNotFoundException {

        GroupParameters groupParameters = groupParametersRepository.findById(id).orElse(null);

        if (groupParameters==null) {
            throw new EntityNotFoundException(GroupParameters.class, "id", id.toString());
        }

        return groupParameters;
    }

    public List<GroupParameters> getAll() {
        return groupParametersRepository.findAll();
    }

    public GroupParameters createGroupParameters(GroupParameters groupParameters) {
        GroupParameters createdGroupParameters = groupParametersRepository.save(groupParameters);
        return createdGroupParameters;
    }

    public GroupParameters saveGroupParameters(Long id, GroupParameters groupParameters) throws EntityNotFoundException {
        GroupParameters groupParametersFromDB = findById(id);
        groupParametersMapper.fillGroupParameters(groupParameters, groupParametersFromDB);
        groupParametersRepository.save(groupParametersFromDB);
        return groupParametersFromDB;
    }

    public void deleteGroupParameters(Long id) throws EntityNotFoundException {
        try {
            groupParametersRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(GroupParameters.class, "id", id.toString());
        }

    }

}

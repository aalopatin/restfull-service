package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.Parameter;
import ru.watchlist.mapper.ParameterMapper;
import ru.watchlist.repository.ParameterRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    ParameterMapper parameterMapper;

    //Repository
    public Parameter findById(Long id) throws EntityNotFoundException {

        Parameter parameter = parameterRepository.findById(id).orElse(null);

        if (parameter==null) {
            throw new EntityNotFoundException(Parameter.class, "id", id.toString());
        }

        return parameter;
    }

    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    public Parameter createParameter(Parameter parameter) {
        Parameter createdParameter = parameterRepository.save(parameter);
        return createdParameter;
    }

    public Parameter saveParameter(Long id, Parameter parameter) throws EntityNotFoundException {
        Parameter parameterFromDB = findById(id);
        parameterMapper.fillParameter(parameter, parameterFromDB);
        parameterRepository.save(parameterFromDB);
        return parameterFromDB;
    }

    public void deleteParameter(Long id) throws EntityNotFoundException {
        try {
            parameterRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Parameter.class, "id", id.toString());
        }

    }
    
}

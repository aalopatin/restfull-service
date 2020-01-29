package ru.watchlist.service;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.Parameter;
import ru.watchlist.mapper.ParameterMapper;
import ru.watchlist.repository.GroupParameterRepository;
import ru.watchlist.repository.ParameterRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.specification.SpecificationBuilder;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    GroupParameterRepository groupParametersRepository;

    @Autowired
    ParameterMapper parameterMapper;

    //Repository
    @Named("findParameterById")
    public Parameter findById(Long id) throws EntityNotFoundException {
        if(id != null) {
            Parameter parameter = parameterRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Parameter.class, "id", id.toString()));
            return parameter;
        } else {
            return null;
        }
    }

    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    public List<Parameter> findAll(String search) {

        SpecificationBuilder<Parameter> builder = new SpecificationBuilder<>(search);
        Specification<Parameter> specification = builder.build();
        return parameterRepository.findAll(specification);

    }

    public Parameter createParameter(Parameter parameter) {
        Parameter createdParameter = parameterRepository.save(parameter);
        return createdParameter;
    }

    public Parameter saveParameter(Long id, Parameter parameter) throws EntityNotFoundException {
        Parameter parameterFromDB = findById(id);
        parameterMapper.fill(parameter, parameterFromDB);
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

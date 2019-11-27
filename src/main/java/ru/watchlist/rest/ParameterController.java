package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.Parameter;
import ru.watchlist.dto.ParameterAbstract;
import ru.watchlist.dto.ParameterIdDTO;
import ru.watchlist.mapper.ParameterMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.ParameterService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/parameters")
public class ParameterController {
    
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ParameterMapper parameterMapper;

    @PostMapping
    public ResponseEntity<ParameterIdDTO> createParameter(@RequestBody ParameterIdDTO parameterIdDTO) {
        Parameter parameter = parameterMapper.fromParameterIdDTO(parameterIdDTO);
        parameter = parameterService.createParameter(parameter);
        return new ResponseEntity<>(parameterMapper.toParameterIdDTO(parameter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterIdDTO> getParameter(@PathVariable Long id) throws EntityNotFoundException {
        Parameter parameter = parameterService.findById(id);
        return new ResponseEntity<>(parameterMapper.toParameterIdDTO(parameter), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends ParameterAbstract>> findAllParameter(Boolean fullObjects) {
        List<Parameter> parameters = parameterService.getAll();

        List<? extends ParameterAbstract> parametersDTO;

        if(fullObjects) {
            parametersDTO = parameterMapper.toParameterDTOList(parameters);
        } else {
            parametersDTO = parameterMapper.toParameterIdDTOList(parameters);
        }

        return new ResponseEntity<>(parametersDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParameterIdDTO> saveParameter(@PathVariable Long id, @RequestBody ParameterIdDTO parameterIdDTO) throws EntityNotFoundException {
        Parameter parameter = parameterMapper.fromParameterIdDTO(parameterIdDTO);
        parameter = parameterService.saveParameter(id, parameter);
        return new ResponseEntity<>(parameterMapper.toParameterIdDTO(parameter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParameter(@PathVariable Long id) throws EntityNotFoundException {
        parameterService.deleteParameter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

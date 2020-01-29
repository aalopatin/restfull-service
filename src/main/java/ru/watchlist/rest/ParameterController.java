package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.Parameter;
import ru.watchlist.dto.Variant;
import ru.watchlist.dto.parameter.ParameterAbstract;
import ru.watchlist.dto.parameter.ParameterIdDTO;
import ru.watchlist.mapper.ParameterMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.ParameterService;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    
    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ParameterMapper parameterMapper;

    @PostMapping
    public ResponseEntity<ParameterIdDTO> createParameter(@RequestBody ParameterIdDTO parameterIdDTO) throws EntityNotFoundException {
        Parameter parameter = parameterMapper.fromIdDTO(parameterIdDTO);
        parameterService.createParameter(parameter);
        return new ResponseEntity<>(parameterMapper.toIdDTO(parameter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ParameterAbstract> getParameter(@PathVariable Long id,
                                                       @RequestParam(value = "variant", required = false) Variant variant) throws EntityNotFoundException {
        Parameter parameter = parameterService.findById(id);
        return new ResponseEntity<>(variantDTO(variant, parameter), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends ParameterAbstract>> findAllParameter(@RequestParam(value = "variant", required = false) Variant variant,
                                                                              @RequestParam(value = "search", required = false) String search) {

        List<Parameter> parameterList = parameterService.findAll(search);
        return new ResponseEntity<>(variantDTOList(variant, parameterList), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParameterIdDTO> saveParameter(@PathVariable Long id, @RequestBody ParameterIdDTO parameterIdDTO) throws EntityNotFoundException {
        Parameter parameter = parameterMapper.fromIdDTO(parameterIdDTO);
        parameter = parameterService.saveParameter(id, parameter);
        return new ResponseEntity<>(parameterMapper.toIdDTO(parameter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParameter(@PathVariable Long id) throws EntityNotFoundException {
        parameterService.deleteParameter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ParameterAbstract variantDTO(Variant variant, Parameter entity) {
        switch (variant) {
            case ID:
                return parameterMapper.toIdDTO(entity);
            default:
                return parameterMapper.toDTO(entity);
        }
    }

    private List<? extends ParameterAbstract> variantDTOList(Variant variant, List<Parameter> entityList) {
        switch (variant) {
            case ID:
                return parameterMapper.toIdDTOList(entityList);
            default:
                return parameterMapper.toDTOList(entityList);
        }
    }
}

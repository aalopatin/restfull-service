package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.Period;
import ru.watchlist.dto.PeriodDTO;
import ru.watchlist.mapper.PeriodMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.PeriodService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/periods")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @Autowired
    private PeriodMapper periodMapper;

    @PostMapping
    public ResponseEntity<PeriodDTO> createPeriod(@RequestBody PeriodDTO periodDTO) {
        Period period = periodMapper.fromPeriodDTO(periodDTO);
        period = periodService.createPeriod(period);
        return new ResponseEntity<>(periodMapper.toPeriodDTO(period), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodDTO> getPeriod(@PathVariable String id) throws EntityNotFoundException {
        Period period = periodService.findById(id);
        return new ResponseEntity<>(periodMapper.toPeriodDTO(period), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PeriodDTO>> getAllPeriod() {
        List<Period> periods = periodService.getAll();
        return new ResponseEntity<>(periodMapper.toPeriodDTOList(periods), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePeriod(@PathVariable String id) throws  EntityNotFoundException {
        periodService.deletePeriod(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

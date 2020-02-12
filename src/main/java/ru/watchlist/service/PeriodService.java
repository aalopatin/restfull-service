package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.Period;
import ru.watchlist.mapper.PeriodMapper;
import ru.watchlist.repository.PeriodRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    @Autowired
    PeriodMapper periodMapper;

    //Repository

    public Period createPeriod(Period period) {

        Integer number;

        switch (period.getType()) {
            case QUARTER:
                number = (period.getEndPeriod().getMonthValue() - 1)/3 + 1;
                period.setNumber(number.byteValue());
                break;
            case HALFYEAR:
                number = (period.getEndPeriod().getMonthValue() - 1)/6 + 1;
                period.setNumber(number.byteValue());
                break;
        }

        return periodRepository.save(period);
    }

    public Period findById(String id) throws EntityNotFoundException {
        if(id != null) {
            return periodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Period.class, "id", id));
        } else {
            return null;
        }
    }

    public List<Period> getAll() {
        return periodRepository.findAll();
    }

    public void deletePeriod(String id) throws EntityNotFoundException {
        try {
            periodRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Period.class, "id", id);
        }
    }
}

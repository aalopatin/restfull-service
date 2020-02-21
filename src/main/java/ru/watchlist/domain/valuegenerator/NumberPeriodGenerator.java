package ru.watchlist.domain.valuegenerator;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import ru.watchlist.domain.Period;

public class NumberPeriodGenerator implements ValueGenerator<Byte> {

    @Override
    public Byte generateValue(Session session, Object owner) {

        Period period = (Period) owner;

        Integer number;

        switch (period.getType()) {
            case QUARTER:
                number = (period.getEndPeriod().getMonthValue() - 1)/3 + 1;
                return number.byteValue();
            case HALFYEAR:
                number = (period.getEndPeriod().getMonthValue() - 1)/6 + 1;
                return number.byteValue();
        }

        return null;
    }
}

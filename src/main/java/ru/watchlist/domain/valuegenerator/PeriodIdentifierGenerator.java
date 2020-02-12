package ru.watchlist.domain.valuegenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import ru.watchlist.domain.Period;
import ru.watchlist.domain.enums.TypePeriod;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PeriodIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        Period period = (Period) object;
        TypePeriod typePeriod = period.getType();
        LocalDate endPeriod = period.getEndPeriod();
        int month = endPeriod.getMonthValue() - 1;
        String id = "";
        String symbol = "";

        switch (typePeriod) {
            case DATE:
                id = endPeriod.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
                break;
            case MONTH:
                id = endPeriod.format(DateTimeFormatter.ofPattern("MMMyyyy", Locale.ENGLISH)).toUpperCase();
                break;
            case QUARTER:
                int quarter = (month/3 + 1);
                symbol = quarter == 1 ? "3M" : quarter + "Q";
                id =  symbol + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
            case HALFYEAR:
                int half = (month/6 + 1);
                symbol = half == 1 ? "6M" : "2H";
                id = symbol + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
            case NINEMONTHS:
                id = "9M" + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
            case TWELVEMONTHS:
                id = "12M" + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
        }

        return id;
    }
}

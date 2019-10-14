package ru.watchlist.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import ru.watchlist.domain.report.period.Period;
import ru.watchlist.domain.report.period.TypePeriod;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class PeriodIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        Period period = (Period) object;
        TypePeriod typePeriod = period.getType();
        LocalDate endPeriod = period.getEndPeriod();
        String id = "";

        switch (typePeriod) {
            case DATE:
                id = endPeriod.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
                break;
            case MONTH:
                id = endPeriod.format(DateTimeFormatter.ofPattern("MMMyyyy", Locale.ENGLISH)).toUpperCase();
                break;
            case TREEMONTHS:
            case SIXMONTHS:
            case NINEMONTHS:
            case TWELVEMONTHS:
            case HALFYEAR:
                id = period.getType().getSymbol() + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
            case QUARTER:
                int month = endPeriod.getMonthValue();
                id = (month/3 + 1) + typePeriod.getSymbol() + endPeriod.format(DateTimeFormatter.ofPattern("yyyy"));
                break;
        }

        return id;
    }
}

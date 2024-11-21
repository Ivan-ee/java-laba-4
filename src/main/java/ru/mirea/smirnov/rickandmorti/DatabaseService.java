package ru.mirea.smirnov.rickandmorti;

import ru.mirea.smirnov.stonks.Valute;

import java.sql.SQLException;
import java.time.LocalDate;

public interface DatabaseService {

    Valute getValuteOfDate(LocalDate date) throws SQLException;

    void saveMaxValuteOfDate(String fio, Valute valute, LocalDate date) throws SQLException;

}

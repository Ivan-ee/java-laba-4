package ru.mirea.smirnov.stonks;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

public class StonksClient {
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {

        DatabaseService databaseService = new DatabaseServiceImpl();

        Retrofit client = new Retrofit
                .Builder()
                .baseUrl("https://www.cbr.ru")
                .addConverterFactory(JacksonConverterFactory.create(new XmlMapper()))
                .build();

        StonksService stonksService = client.create(StonksService.class);

        LocalDate birthDate = LocalDate.of(2003, 5, 2);

        Response<DailyCurs> response = stonksService
                .getDailyCurs(birthDate
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).execute();

        if (!response.isSuccessful() || response.body() == null) {
            System.out.println("Ошибка при получении данных с сервера.");
            return;
        }

        DailyCurs dailyCurs = response.body();
        Optional<Valute> maxValute = dailyCurs.getValutes().stream()
                .filter(valute -> !valute.getName().equals("СДР (специальные права заимствования)"))
                .max(Comparator.comparingDouble(Valute::getValue));


        if (maxValute.isPresent()) {
            System.out.println(maxValute.get());

            Valute mv = maxValute.get();

            databaseService.saveMaxValuteOfDate("смирновиа", mv, birthDate);
            System.out.printf("Валюта сохранена %s", mv);
        }

        Valute myValute = databaseService.getValuteOfDate(birthDate);

        System.out.printf("Получена валюта из бд %s", myValute);
    }

}

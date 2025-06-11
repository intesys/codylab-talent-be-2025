package it.intesys.codylab.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter ITALIAN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatDateToItalian(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(ITALIAN_FORMATTER);
    }

    public static LocalDate parseItalianDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        return LocalDate.parse(date, ITALIAN_FORMATTER);
    }

}

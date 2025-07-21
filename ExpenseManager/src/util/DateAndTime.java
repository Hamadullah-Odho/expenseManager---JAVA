package util;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
final public class DateAndTime {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    public String getCurrentDate(){return String.valueOf(date);}
    public String getCurrentTime(){return String.valueOf(time.format(formatter));}
}

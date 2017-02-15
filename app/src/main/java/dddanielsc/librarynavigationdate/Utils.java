package dddanielsc.librarynavigationdate;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

/**
 * Created by dddanielsc on 13/02/17.
 */

public class Utils {


    /*retona no formato dd/mm/yyyy*/
    public static DateTimeFormatter formatterJodaTimeBR (){
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendDayOfMonth(2)
                .appendLiteral("/")
                .appendMonthOfYear(2)
                .appendLiteral("/")
                .appendYear(4, 0)
                .toFormatter();

        return dtf;
    }

    /*retona no formato yyyy-mm-dd*/
    public static DateTimeFormatter formatterJodaTimeSql(){
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendYear(4, 0)
                .appendLiteral("-")
                .appendMonthOfYear(2)
                .appendLiteral("-")
                .appendDayOfMonth(2)
                .toFormatter();

        return dtf;
    }

    /*retorna no formato yyyyMMdd'T'HHmmss'Z'*/
    public static DateTimeFormatter formatterJodaTimeISO8601(){

        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmss'Z");
//        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
//                .appendYear(4, 0)
//                .appendMonthOfYear(2)
//                .appendDayOfMonth(2)
//                .appendLiteral("T")
//                .appendHourOfDay(2)
//                .appendMinuteOfHour(2)
//                .appendSecondOfMinute(2)
//                .appendLiteral("Z")
//                .toFormatter();
        return dtf;
    }

    /*retorna no formato mm_literal/yyyy*/
    public static DateTimeFormatter formatterJodaTimeMonthYearBR(){
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendMonthOfYearText()
                .appendLiteral("/")
                .appendYear(4,0)
                .toFormatter();

        return dtf;
    }

    /*retorna formato  dd/mm*/
    public static DateTimeFormatter formatterJodaTimeDayMonthBR (){
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendDayOfMonth(2)
                .appendLiteral("/")
                .appendMonthOfYear(2)
                .toFormatter();

        return dtf;
    }

    /*retorna no formato dd/mm à dd/mm*/
    public static String formatterJodaTimeWeekBR(DateTime dt){
        String date="";

        date = dt.withDayOfWeek(1).toString(formatterJodaTimeDayMonthBR()) + " a "  + dt.withDayOfWeek(7).toString(formatterJodaTimeDayMonthBR());

        return date;
    }
}

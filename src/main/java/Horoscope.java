import java.time.DateTimeException;
import java.time.MonthDay;
import java.util.InputMismatchException;

class Horoscope {
    private String sign;

    String spotSign (int month, int day) {
        try {
            MonthDay monthDay = MonthDay.of(month, day);
            if (monthDay.isAfter(MonthDay.of(12, 21)) || monthDay.isBefore(MonthDay.of(1, 20))) {
                sign = "Capricorn"; //System.out.println("Capricorn");
            } else if (monthDay.isAfter(MonthDay.of(1, 19)) && monthDay.isBefore(MonthDay.of(2, 19))) {
                sign = "Aquarius"; //System.out.println("Aquarius");
            } else if (monthDay.isAfter(MonthDay.of(2, 18)) && monthDay.isBefore(MonthDay.of(3, 21))) {
                sign = "Pisces"; //System.out.println("Pisces");
            } else if (monthDay.isAfter(MonthDay.of(3, 20)) && monthDay.isBefore(MonthDay.of(4, 20))) {
                sign = "Aries"; //System.out.println("Aries");
            } else if (monthDay.isAfter(MonthDay.of(4, 19)) && monthDay.isBefore(MonthDay.of(5, 21))) {
                sign = "Taurus"; //System.out.println("Taurus");
            } else if (monthDay.isAfter(MonthDay.of(5, 20)) && monthDay.isBefore(MonthDay.of(6, 21))) {
                sign = "Gemini"; //System.out.println("Gemini");
            } else if (monthDay.isAfter(MonthDay.of(6, 20)) && monthDay.isBefore(MonthDay.of(7, 23))) {
                sign = "Cancer"; //System.out.println("Cancer");
            } else if (monthDay.isAfter(MonthDay.of(7, 22)) && monthDay.isBefore(MonthDay.of(8, 23))) {
                sign = "Leo" ; //System.out.println("Leo");
            } else if (monthDay.isAfter(MonthDay.of(8, 22)) && monthDay.isBefore(MonthDay.of(9, 23))) {
                sign = "Virgo"; // System.out.println("Virgo");
            } else if (monthDay.isAfter(MonthDay.of(9, 22)) && monthDay.isBefore(MonthDay.of(10, 23))) {
                sign = "Libra"; //System.out.println("Libra");
            } else if (monthDay.isAfter(MonthDay.of(10, 22)) && monthDay.isBefore(MonthDay.of(11, 22))) {
                sign = "Scorpio"; System.out.println("Scorpio");
            } else if (monthDay.isAfter(MonthDay.of(11, 21)) && monthDay.isBefore(MonthDay.of(12, 22))) {
                sign = "Sagittarius"; //System.out.println("Sagittarius");
            }
        } catch (DateTimeException | InputMismatchException exp) {
            System.out.println("exp");
        }
        return sign;
    }
}

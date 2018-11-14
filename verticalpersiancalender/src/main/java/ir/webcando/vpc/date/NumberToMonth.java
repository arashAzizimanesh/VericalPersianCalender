package ir.webcando.vpc.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ashkan on 27/12/2016.
 */
public class NumberToMonth {
    List<String> moonList;

    public NumberToMonth() {
        moonList = new ArrayList<>();
        moonList.add("فروردین");
        moonList.add("اردیبهشت");
        moonList.add("خرداد");
        moonList.add("تیر");
        moonList.add("مرداد");
        moonList.add("شهریور");
        moonList.add("مهر");
        moonList.add("آبان");
        moonList.add("آذر");
        moonList.add("دی");
        moonList.add("بهمن");
        moonList.add("اسفند");
    }


    public String getMonth(int num) {

        return moonList.get(num - 1);
    }

    public int getMonthNumber(String monthName) {

        for (int i = 0; i < moonList.size(); i++) {

            String name = moonList.get(i);
            if (name.equals(monthName)) {
                return i + 1;

            }

        }
        return 0;


    }


    public static String sayDayName(Date d) {
        DateFormat f = new SimpleDateFormat("EEEE");
        try {
            return f.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

//        شنبه        Saturday
//
//        یکشنبه     Sunday
//
//        دوشنبه     Monday
//
//        سه شنبه    Tuesday
//
//        چهارشنبه Wednesday
//
//        پنجشنبه   Thursday
//
//        جمعه      Friday
    }

}

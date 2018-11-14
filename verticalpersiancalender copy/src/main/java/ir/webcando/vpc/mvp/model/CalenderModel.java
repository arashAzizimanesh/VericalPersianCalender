package ir.webcando.vpc.mvp.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.webcando.vpc.date.NumberToMonth;
import ir.webcando.vpc.date.PersianDate;
import ir.webcando.vpc.info.CalenderInfo;
import ir.webcando.vpc.info.DateInfo;
import ir.webcando.vpc.info.DayInfo;

/**
 * Created by arashazizi on 2/19/18.
 */

public class CalenderModel {

    public CalenderInfo getListCalender(int skipMonth) {

        PersianDate persianDate = new PersianDate();
        NumberToMonth numberToMonth = new NumberToMonth();

        String dayShamsi = persianDate.getDayShamsi();
        int monthShamsi = Integer.parseInt(persianDate.getMonthShamsi());
        int yearShamsi = Integer.parseInt(persianDate.getYearShamsi());

        CalenderInfo calenderInfo = new CalenderInfo();

        calenderInfo.setDefDay(dayShamsi);

        List<DateInfo> dateInfos = new ArrayList<>();
        for (int i = 0; i < skipMonth; i++) {
//            monthNumber = monthShamsi + (i + 1);
            DateInfo dateInfo = new DateInfo();

//            if (i == 0) {

            dateInfo.setMonthNumber(monthShamsi);
            dateInfo.setMonthName(numberToMonth.getMonth(monthShamsi));
            dateInfo.setYear("" + yearShamsi);


            Log.d("sdsdsdsd", "getListCalender: " + monthShamsi + " " + yearShamsi);

//            }else {
//
//
//
//
//            }


            int dayLoop = 0;
            if (monthShamsi == 12) {

                boolean kabise = persianDate.kabise(yearShamsi);
                if (kabise) {

                    dayLoop = 30;
                } else {

                    dayLoop = 29;
                }


            } else if (monthShamsi >= 1 && monthShamsi <= 6) {

                dayLoop = 31;

            } else if (monthShamsi > 6 && monthShamsi <= 11) {
                dayLoop = 30;

            }

            List<DayInfo> dayInfos = new ArrayList<>();
            for (int j = 0; j < dayLoop; j++) {

                DayInfo dayInfo = new DayInfo();
//                String s = persianDate.En_Date(yearShamsi + "/" + monthShamsi + "/" + (j + 1));
                String s = persianDate.jalali_to_gregorian(yearShamsi , monthShamsi, (j + 1));
                Log.d("sssdsghhh", "getListCalender: "+ yearShamsi + "/" + monthShamsi + "/" + (j + 1)+ " "+s);

                try {
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(s);
                    dayInfo.setDayName(NumberToMonth.sayDayName(date1));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dayInfo.setDayNumber("" + (j + 1));
                dayInfos.add(dayInfo);
            }

            dateInfo.setDayInfos(dayInfos);

            dateInfos.add(dateInfo);


            if (monthShamsi == 12) {
                yearShamsi++;
                monthShamsi = 1;
            } else {
                monthShamsi++;
            }
        }

        calenderInfo.setDateInfo(dateInfos);


        return calenderInfo;

    }
}

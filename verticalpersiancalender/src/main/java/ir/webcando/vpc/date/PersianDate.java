package ir.webcando.vpc.date;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PersianDate {
    private String yearShamsi;
    private String dayShamsi;
    private String monthShamsi;

    private String Farsi_Date = null;
    private String English_Date = null;
    private int The_Year;
    private int The_Month;
    private int The_Day;


    public String getDayShamsi() {
        return dayShamsi;
    }

    public String getMonthShamsi() {
        return monthShamsi;
    }

    public String getYearShamsi() {
        return yearShamsi;
    }

    public PersianDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String curentDateandTime = sdf.format(new Date());
        String year = curentDateandTime.substring(0, 4);
        String month = curentDateandTime.substring(4, 6);
        String day = curentDateandTime.substring(6, 8);
        int Y = Integer.valueOf(year);
        int M = Integer.valueOf(month);
        int D = Integer.valueOf(day);
        shamsiDate(Y, M, D);
    }

    public String todayShamsi() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String curentDateandTime = sdf.format(new Date());
        String year = curentDateandTime.substring(0, 4);
        String month = curentDateandTime.substring(4, 6);
        String day = curentDateandTime.substring(6, 8);
        int Y = Integer.valueOf(year);
        int M = Integer.valueOf(month);
        int D = Integer.valueOf(day);
        return shamsiDate(Y, M, D);
    }


    public String shamsi‌‌‌ByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
        String curentDateandTime = sdf.format(date);
        String year = curentDateandTime.substring(0, 4);
        String month = curentDateandTime.substring(4, 6);
        String day = curentDateandTime.substring(6, 8);
        int Y = Integer.valueOf(year);
        int M = Integer.valueOf(month);
        int D = Integer.valueOf(day);
        final String time = curentDateandTime.split(" ")[1];
//

        return shamsiDate(Y, M, D) + " " + time;
    }

    public boolean gLeapYear(int year) {
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
            return true;
        else
            return false;
    }

    public boolean sLeapYear(int year) {
        int[] ary = {1, 5, 9, 13, 17, 22, 26, 30};
        boolean result = false;
        int b = year % 33;
        if (Arrays.asList(ary).contains(b))
            result = true;
        return result;
    }

    public String shamsiDate(int gyear, int gmonth, int gday) {
        int[] _gl = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        int[] _g = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        int deydiffjan = 10;
        int gd = 0;
        int sd = 0;
        int sm = 0;
        int gmod = 0;
        int sy = 0;

        if (gLeapYear(gyear - 1))
            deydiffjan = 11;
        if (gLeapYear(gyear))
            gd = _gl[gmonth - 1] + gday;
        else
            gd = _g[gmonth - 1] + gday;
        if (gd > 79) {
            sy = gyear - 621;
            gd = gd - 79;
            if (gd <= 186) {
                gmod = gd % 31;
                switch (gmod) {
                    case 0:
                        sd = 31;
                        sm = (int) (gd / 31);
                        break;
                    default:
                        sd = gmod;
                        sm = (int) (gd / 31) + 1;
                }
            } else {
                gd = gd - 186;
                gmod = gd % 30;
                switch (gmod) {
                    case 0:
                        sd = 30;
                        sm = (int) (gd / 30) + 6;
                        break;
                    default:
                        sd = gmod;
                        sm = (int) (gd / 30) + 7;
                }
            }
        } else {
            sy = gyear - 622;
            gd = gd + deydiffjan;
            gmod = gd % 30;
            switch (gmod) {
                case 0:
                    sd = 30;
                    sm = (int) (gd / 30) + 9;
                    break;
                default:
                    sd = gmod;
                    sm = (int) (gd / 30) + 10;
            }
        }
        yearShamsi = String.valueOf(sy);
        monthShamsi = String.valueOf(sm);
        dayShamsi = String.valueOf(sd);
        return String.valueOf(sy) + '/' + String.valueOf(sm) + '/' + String.valueOf(sd);
    }

    public boolean kabise(int year) {
        int a = 0, b = 1309, c;
        c = year;
        for (int i = 1309; i <= c - 4; i += 4) {
            b += 4;
            a += 1;
            if (a % 8 == 0)
                b++;
        }
        if (c == b)
            return true;
        else
            return false;
    }

    public static String jalali_to_gregorian(int jy, int jm, int jd){
        int gy;
        if(jy>979){
            gy=1600;
            jy-=979;
        }else{
            gy=621;
        }
        int days = (365 * jy) + (((int)(jy / 33)) * 8) + ((int)(((jy % 33) + 3) / 4)) + 78 + jd + ((jm < 7)?(jm - 1) * 31:((jm - 7) * 30) + 186);
        gy += 400 * ((int)(days / 146097));
        days %= 146097;
        if(days > 36524){
            gy += 100 * ((int)(--days / 36524));
            days %= 36524;
            if (days >= 365)days++;
        }
        gy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            gy += (int)((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int gd = days + 1;
        int[] sal_a = {0,31,((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))?29:28,31,30,31,30,31,31,30,31,30,31};
        int gm;
        for(gm = 0;gm < 13;gm++){
            int v = sal_a[gm];
            if(gd <= v)break;
            gd -= v;
        }
        int[] out = {gy,gm,gd};


        return  gd + "/" + gm + "/" + gy;
    }






}

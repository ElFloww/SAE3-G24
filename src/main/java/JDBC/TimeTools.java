package JDBC;

public class TimeTools{
    public static java.sql.Time addTime(java.sql.Time T1, java.sql.Time T2)
    {
        int second = T1.getSeconds()+T2.getSeconds();
        int minute = T1.getMinutes()+T2.getMinutes();
        int hour = T1.getHours()+T2.getHours();

        if(second >=60){ second = second - 60; minute = minute +1;}
        if(minute >=60){ minute = minute - 60; hour = hour -1;}
        if(hour >=24){ hour = hour -24;}
        return new java.sql.Time(hour,minute,second);
    }
}

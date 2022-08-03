/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DLC;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
public class Extension {
    public Date AddDate(int borrowdate){
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);
        c.add(Calendar.DATE, borrowdate);
        return new Date(c.getTimeInMillis());
    }
    public Date AddMonth(int borrowMonth){
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);
        c.add(Calendar.MONTH, borrowMonth);
        return new Date(c.getTimeInMillis());
    }
    public Date AddDate(long borrowdate){
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);
        c.add(Calendar.DATE, (int)borrowdate);
        return new Date(c.getTimeInMillis());
    }
    public long DiffDate(Date requestDate, Date expiredDate){
        Date diff = new Date(expiredDate.getTime() - requestDate.getTime());
        long day_diff = diff.getTime() / (1000*60*60*24);
        return day_diff;
    }
    
}

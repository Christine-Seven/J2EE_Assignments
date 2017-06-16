package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Seven on 14/06/2017.
 */
public class Str2Calendar {

    public static Calendar str2Calendar(String s){
        try {
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(s);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

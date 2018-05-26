package com.zippit.zippitbackend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by akash.mercer on 07-Jul-17.
 */
public class DateOperations {

    public static String getDateStringForDump(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy,HH:mm");
        return simpleDateFormat.format(date);
    }

}

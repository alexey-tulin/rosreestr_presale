package ru.rosreestr.utils;

import org.apache.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Tatiana Chukina on 29.09.2016 2:36.
 * <p/>
 */
public class CommonUtils {
    private static final Logger LOG = Logger.getLogger(CommonUtils.class);

    public static String getCurrentYear(){
        DateFormat df = new SimpleDateFormat("yy");
        String formattedDate = df.format(Calendar.getInstance().getTime());
        return formattedDate;
    }

    public static XMLGregorianCalendar getXmlGregorianCurrentDate(){
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;
    }
}

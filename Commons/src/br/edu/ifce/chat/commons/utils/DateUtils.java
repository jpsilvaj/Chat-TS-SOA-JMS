package br.edu.ifce.chat.commons.utils;

import java.text.DateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getFormatedHourFromDate(Date date){
		DateFormat timeFormat = DateFormat.getTimeInstance();
		return "["+ timeFormat.format(date) + "] ";
	}
	
	public static String getFormatedDate(Date date){
		DateFormat dateFormat = DateFormat.getDateInstance();
		return "[" + dateFormat.format(date) + "] ";
	}
}

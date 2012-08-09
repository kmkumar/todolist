package com.rackspace.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

	public static XMLGregorianCalendar getGDate(String dateStr) {

		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		XMLGregorianCalendar dateg = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar dateXml = new GregorianCalendar();
		dateXml.setTime(date);
		try {
			dateg = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					dateXml);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return dateg;
	}

}

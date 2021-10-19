<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DateFormat" %>
<%
	Date date = Calendar.getInstance().getTime();
	Locale locale = request.getLocale();
	//Locale locale = Locale.CHINESE;
	String date2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale).format(date);
	out.print(date2);
%>
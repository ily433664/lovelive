package com.lovelive.common.uitls;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author dHe
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy-MM", "yyyyMM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null) {
            return null;
        }
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getNowYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    private static String[] parseExcelPatterns = {"yyyy-MM-dd", "yyyy-MM-dd", "yyyy-MM-dd",
            "yyyyMM", "yyyy/MM/dd", "yyyy/MM/dd", "yyyy/MM/dd"};

    private static String[] parseExcelUsPatterns = {"dd-MMM-yy", "dd-MMM-yyyy", "yyyy-MMM-dd", "yyyy-MMM-dd", "yyyy-MMM-dd",
            "yyyyMMM", "yyyy/MMM/dd", "yyyy/MMM/dd", "yyyy/MMM/dd"};

    public static Date parseExcelDate(Object str) {
        if (str == null) {
            return null;
        }
        String s = str.toString().trim();
        Date date = null;
        try {
            date = parseDate(s, parseExcelPatterns);
				/*
				String[] ss = null;
				String ch = "";
				if (s.indexOf("/") != -1) {
					ss = s.split("/");
					ch = "/";
				} else  if (s.indexOf("-") != -1){
					ss = s.split("-");
					ch = "-";
				}
				String p = "";
				Locale lo = new Locale("US");
				boolean us = false;
				if (ss != null) {
					int index = 0;
					for (String a : ss) {
						if (!StringUtils.isNumeric(a)) us = true;
						if (index == 0) {
							if (a.length() == 4) {
								p = "yyyy";
							}
							if (a.length() == 3) {
								p = "MMM";
							}
							if (a.length() == 2) {
								p = "dd";
							}
						}
						if (a.length() == 4) {
							p = p+ch+"yyyy";
						}
						if (a.length() == 3) {
							p = p+ch+"MMM";
						}
						if (a.length() == 2) {
							p = p+ch+"yyyy";
						}
						index++;
					}
				}*/
            return date;
        } catch (ParseException e) {
            String regex = ".*[a-zA-Z]+.*";
            Matcher m = Pattern.compile(regex).matcher(s);
            if (m.matches()) {
                for (String pp : parseExcelUsPatterns) {
                    if (pp.length() == s.length()) {
                        SimpleDateFormat sdf = new SimpleDateFormat(pp, new Locale("US"));
                        try {
                            date = sdf.parse(s);
                            if (date != null) return date;
                        } catch (ParseException ee) {

                        }
                    }
                }

            }
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date afterDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);//让日期加1
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static String dateRange(Date beginDate, Date endDate, String pattern) {
        StringBuffer buf = new StringBuffer(64);
        if (beginDate != null) {
            buf.append(DateUtils.formatDate(beginDate, pattern));
        }
        if (endDate != null) {
            buf.append("到");
            buf.append(DateUtils.formatDate(endDate, pattern));
        } else if (beginDate != null) {
            buf.append("起");
        }
        return buf.toString();

    }

    public static String workYears(Date beginDate) {
        StringBuffer buf = new StringBuffer(64);
        Date date = new Date();

        if (beginDate != null) {
            if (date.before(beginDate)) {
                return "0";
            }
            Calendar dayEnd = Calendar.getInstance();
            Calendar dayBegin = Calendar.getInstance();
            dayBegin.setTime(beginDate);

            int result = dayEnd.get(Calendar.MONTH) + dayEnd.get(Calendar.YEAR) * 12
                    - dayBegin.get(Calendar.MONTH) - dayBegin.get(Calendar.YEAR) * 12;
            int years = result / 12;
            int left = result - (years * 12);
            if (left >= 6) {
                years++;
                buf.append(years + "年");
            } else if (left > 0) {
                buf.append(years + "年半");
            } else {
                buf.append(years + "年");
            }
        }
        return buf.toString();
    }

    /**
     * 比较当前时间
     *
     * @param beginDate
     * @param endDate
     * @return -1：表示未开始 0表示在时间段内， 1表示超时
     */
    public static int checkNowDate(Date beginDate, Date endDate) {
        if (beginDate == null && endDate == null) {
            return 0;
        }
        Date now = new Date();
        if (beginDate != null && now.before(beginDate)) {
            return -1;
        }
        if (endDate != null && endDate.before(now)) {
            return 1;
        }
        return 0;
    }

    /**
     * 合并两个日期的年月日
     *
     * @param date1
     * @param date2
     * @param patterns
     * @return
     */
    public static String getTime(Date date1, Date date2, String... patterns) {
        StringBuilder sb = new StringBuilder();

        String[] ymds = new String[]{"yyyy-MM-dd", "yyyy/MM/dd"};
        String pattern = "yyyy-MM-dd HH:mm:ss";
        if (patterns != null && patterns.length > 0) {
            pattern = patterns[0];
        }

        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            for (String ymd : ymds) {
                if (pattern.startsWith(ymd)) {
                    if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                            && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                            && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)) {
                        sb.append(formatDate(date1, ymd + " "));
                        pattern = pattern.replace(ymd, "").trim();
                    }
                }
            }

            if (pattern.length() > 0) {
                if (cal1.getTimeInMillis() <= cal2.getTimeInMillis()) {
                    sb.append(formatDate(date1, pattern)).append("~").append(formatDate(date2, pattern));
                } else {
                    sb.append(formatDate(date2, pattern)).append("~").append(formatDate(date1, pattern));
                }
            }

        } else if (date1 != null) {
            sb.append(formatDate(date1, pattern));
        } else if (date2 != null) {
            sb.append(formatDate(date2, pattern));
        }
        return sb.toString();
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

        System.out.println(DateUtils.formatDate(DateUtils.parseDate("2017-10"), "yyyy-MM"));
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));

        String str = "345-343-34";
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        System.out.println(m.matches());//true

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy", new Locale("US"));
        System.out.println(parseExcelDate("30-Jul-07") + "------");


        System.out.println(formatDate(parseDate("30-Jul-07")));
        String s = "30-Jul-07";

        System.out.println(DateFormatUtils.format(new Date(), "dd-MMM-yy", new Locale("US")));


        Date d = new Date();
        System.out.println(formatDateTime(afterDate(d, -7)));

        Date date1 = DateUtils.parseDate("2019-11-20 18:37:32");
        Date date2 = DateUtils.parseDate("2019-11-20 13:46:20");
        System.out.println("getTime : " + getTime(date1, date2, "yyyy-MM-dd HH:mm:ss"));

    }
}

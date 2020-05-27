package com.wechat.web.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;


/**
 * 日期工具类, 继承org.apache.commons.lang3.time.DateUtils类
 */
public class DateTimeUtil extends DateUtils {

  // Begin modified by maojj 2016-07-04 添加"HH:mm"
  private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
    "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "HH:mm"};
  public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String DATE_NUM_FORMAT = "yyyyMMdd";
  // End modified by maojj 2016-07-04
  public static final long SECOND = 1 * 1000;
  public static final long MINUTE = 60 * SECOND;
  public static final long HOUR = 60 * MINUTE;
  public static final long DAY = 24 * HOUR;
  private static final ZoneId zoneId = ZoneId.systemDefault();

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd）
   */
  public static String getDate() {
    return getDate(DATE_FORMAT);
  }

  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   */
  public static String getDate(String pattern) {
    LocalDateTime dateTime = LocalDateTime.now(zoneId);
    return dateTime.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static Date getUTCDate() {
    Calendar calendar = Calendar.getInstance();
    //取得时间偏移量：
    int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    //取得夏令时差：
    int dstOffset = calendar.get(Calendar.DST_OFFSET);
    //从本地时间里扣除这些差量，取得UTC时间：
    calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
    return calendar.getTime();
  }

  public static Date getDateByTime(Long time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time);
    return calendar.getTime();
  }

  public static Date getUTCDateByTime(Long time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time);
    //取得时间偏移量：
    int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
    //取得夏令时差：
    int dstOffset = calendar.get(Calendar.DST_OFFSET);
    //从本地时间里扣除这些差量，取得UTC时间：
    calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
    return calendar.getTime();
  }

  /**
   * @param date    时间
   * @param pattern 格式化
   * @return 字符串
   * @desc 格式化时间
   */
  public static String getDate(Date date, String pattern) {
    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
    return dateTime.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 格式化时间
   */
  public static String dateFormat(Date date) {
    return getDate(date, TIME_FORMAT);
  }

  /**
   * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   */
  public static String formatDate(Date date, Object... pattern) {
    String formatDate = null;
    if (pattern != null && pattern.length > 0) {
      formatDate = DateFormatUtils.format(date, pattern[0].toString());
    } else {
      formatDate = DateFormatUtils.format(date, DATE_FORMAT);
    }
    return formatDate;
  }

  /**
   * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
   */
  public static String formatDateTime(Date date) {
    return formatDate(date, TIME_FORMAT);
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
    return formatDate(new Date(), TIME_FORMAT);
  }

  /**
   * 得到当前年份字符串 格式（yyyy）
   */
  public static String getYear() {
    return formatDate(new Date(), "yyyy");
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
   * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
   * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
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

  public static Date parseStr2Date(String dateStr, String pattern) {
    if (StringUtils.isEmpty(dateStr)) {
      return new Date();
    }
    if (StringUtils.isBlank(pattern)) {
      pattern = TIME_FORMAT;
    }
    DateTimeFormatter fomatter = DateTimeFormatter
      .ofPattern(pattern);
    LocalDateTime dateTime = LocalDateTime.parse(dateStr, fomatter);
    return Date.from(dateTime.atZone(zoneId).toInstant());
  }

  /**
   * 功能：字符串转换为time.
   */
  public static Date parseStrToTime(String dateStr) {
    return parseStr2Date(dateStr, TIME_FORMAT);
  }

  /**
   * 功能：获取当前日期.
   */
  public static Date getCurrDay() {
    LocalDate dateTime = LocalDate.now();
    Instant instant = dateTime.atStartOfDay(zoneId).toInstant();
    return Date.from(instant);
  }

  public static Date getDateStart(Date date) {
    if (date == null) {
      return null;
    }
    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
    Instant instant = dateTime.toLocalDate().atStartOfDay(zoneId).toInstant();
    return Date.from(instant);
  }

  public static Date getDateEnd(Date date) {
    if (date == null) {
      return null;
    }
    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
    String dateEndStr = dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + " 23:59:59";
    dateTime = LocalDateTime.parse(dateEndStr, DateTimeFormatter.ofPattern(TIME_FORMAT));
    Instant instant = dateTime.atZone(zoneId).toInstant();
    return Date.from(instant);
  }

  //获取beginDate加上Days天后的日期,输出格式是yyyy-MM-dd
  public static Date dayDiff(Date beginDate, int days) {
    LocalDateTime dateTime = LocalDateTime.ofInstant(beginDate.toInstant(), zoneId);
    dateTime = dateTime.plusDays(days);
    Instant instant = dateTime.toLocalDate().atStartOfDay(zoneId).toInstant();
    return Date.from(instant);
  }

  public static Long getTimeAfterDay(int day) {
    Calendar ca = Calendar.getInstance();
    ca.add(Calendar.DATE, day);// num为增加的天数，可以改变的
    return ca.getTime().getTime() / 1000;
  }

  /**
   * 时间滚动, 按天, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfDay(Date date, int amount, boolean up) {
    return up ? new Date(date.getTime() + ((long) amount) * DAY)
      : new Date(date.getTime() - ((long) amount) * DAY);
  }

  /**
   * 判断字符串是否是日期
   */
  public static boolean isDate(String timeString) {
    DateTimeFormatter fomatter = DateTimeFormatter
      .ofPattern(DATE_FORMAT);
    try {
      LocalDateTime.parse(timeString, fomatter);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * 功能：获取系统本地时间的时间戳(精确到秒.)
   */
  public static Long getTimeStamp() {
    return new Date().getTime() / 1000;
  }

  /**
   * 功能：获取UTC时间的时间戳(精确到秒.)
   */
  public static Long getUTCTimeStamp() {
    return getUTCDate().getTime() / 1000;
  }

  /**
   * 功能：获取指定时间的时间戳(精确到秒).
   */
  public static Long getTimeFromDay(Date date) {
    return date.getTime() / 1000;
  }

  /**
   * 获取系统时间Date
   */
  public static Date getSysDate() {
    return new Date();
  }

  /**
   * 日期大小比较(与当前日期比较),并返回相差天数.
   *
   * @param oDate     待比较的日期.
   * @param basicDate 基准日期.
   * @return 返回结果:如参数日期晚于当前日期，则返回相差天数，如早于当前日期，则返回负值.
   */
  public static int daysOfTwo(Date oDate, Date basicDate) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(oDate);
    int day1 = cal1.get(Calendar.DAY_OF_YEAR);
    int year1 = cal1.get(Calendar.YEAR);
    int hour1 = cal1.get(Calendar.HOUR_OF_DAY);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(basicDate);
    int day2 = cal2.get(Calendar.DAY_OF_YEAR);
    int year2 = cal2.get(Calendar.YEAR);
    int hour2 = cal2.get(Calendar.HOUR_OF_DAY);

    //小时数差额大于0，则加一天.
    int hourDay = ((hour1 > hour2) ? 1 : 0);
    if (year1 != year2)   //同一年
    {
      int timeDistance = 0;
      for (int i = year2; i < year1; i++) {
        if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
        {
          timeDistance += 366;
        } else    //不是闰年
        {
          timeDistance += 365;
        }
      }

      return timeDistance + (day1 - day2) + hourDay;
    } else    //不同年
    {
      return day1 - day2 + hourDay;
    }
  }

  //比较日期大小，beginDate小于等于endDate时，返回为true
  public static Boolean dateCheck(String beginDate, String endDate) {
    DateTimeFormatter fomatter = DateTimeFormatter
      .ofPattern(TIME_FORMAT);
    try {
      LocalDateTime dateTime = LocalDateTime.parse(beginDate, fomatter);
      Instant instant = dateTime.atZone(zoneId).toInstant();
      Date begindate = Date.from(instant);
      dateTime = LocalDateTime.parse(endDate, fomatter);
      instant = dateTime.atZone(zoneId).toInstant();
      Date enddate = Date.from(instant);

      if (!(begindate.after(enddate))) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 功能：判断当前时间与被检查时间的时间差是否超过条件.
   *
   * @param timestamp 被检查时间.
   * @param minutes   时间差(单位：分钟)
   */
  public static boolean belongMinutes(long timestamp, long minutes) {
    long currentTime = System.currentTimeMillis();
    timestamp += minutes * 60 * 1000;
    return (timestamp > currentTime);
  }

  public static Integer getMonthFirstNum() {
    LocalDate today = LocalDate.now(zoneId);
    LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
    return Integer.parseInt(firstDayOfMonth.format(DateTimeFormatter.ofPattern(DATE_NUM_FORMAT)));
  }

  public static Integer getCurrentDayNum() {
    LocalDate today = LocalDate.now(zoneId);
    return Integer.parseInt(today.format(DateTimeFormatter.ofPattern(DATE_NUM_FORMAT)));
  }

  public static Integer getRangeDayNum(int num) {
    LocalDate date = LocalDate.now(zoneId);
    date = date.plusDays(-num);
    return Integer.parseInt(date.format(DateTimeFormatter.ofPattern(DATE_NUM_FORMAT)));
  }

  public static String getMiSecondStr() {
    LocalDateTime dateTime = LocalDateTime.now(zoneId);
    return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
  }

  /**
   * 获取当前时间上个月第一天
   * 描述:<描述函数实现的功能>.
   */
  public static Date getLastMonthDate() {
    //获取前一个月第一天
    LocalDate date = LocalDate.now(zoneId);
    date = date.plusMonths(-1);
    LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
    Instant instant = firstDayOfMonth.atStartOfDay(zoneId).toInstant();
    return Date.from(instant);
  }

  /**
   * 功能：获取当前所在时区.
   */
  public static int getCurrTimeZone() {
    String timeZone = DateFormatUtils.format(new Date(), "ZZ");
    return Integer.parseInt(timeZone.substring(0, timeZone.indexOf(":")));
  }

    public static void main(String[] args) {
        String str = "2019-04-01 11:18:20";
        Date time = parseStrToTime(str);
        System.out.println(time);
        System.out.println(getCurrDay());
        System.out.println(getDateStart(time));
        System.out.println(getDateEnd(time));
        System.out.println(dateFormat(time));
        System.out.println(isDate("12345678"));
        System.out.println(dateCheck("2019-04-01 11:50:50", "2019-04-01 11:50:51"));
        System.out.println(dayDiff(time, 5));
        System.out.println(getMonthFirstNum());
        System.out.println(getCurrentDayNum());
        System.out.println(getRangeDayNum(3));
        System.out.println(getMiSecondStr());
        System.out.println(getLastMonthDate());
    }

}

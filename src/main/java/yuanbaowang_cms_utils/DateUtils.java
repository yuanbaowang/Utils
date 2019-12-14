/**
 * 
 */
package yuanbaowang_cms_utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 袁保旺
 *
 * 2019年12月6日 下午1:18:37 
 */
public class DateUtils {
	
	/**
	 * 一天等于多少毫秒
	 */
	static final int millSecondsPerDay = 1000*60*60*24;
	
	
	
	/**
	 * 计算年龄
	 * @param birthday
	 * @return 返回年龄
	 */
	public static int getAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		//传过来，要计算年龄的日期参数
		int calendarYear = calendar.get(Calendar.YEAR);
		int calendarMonth = calendar.get(Calendar.MONTH);
		int calendarDay = calendar.get(Calendar.DATE);

		//继续计算当前的日期
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DATE);
		
		//计算年龄
		int age = currentYear - calendarYear;
		if(currentMonth < calendarYear) {
			age--;
		}else if(currentMonth == calendarMonth && currentDay < calendarDay) {
			age--;
		}
		return age;
	}
	
	
	
	/**
	 * 计算剩余多少天数
	 * @param future
	 * @return 返回年龄
	 */
	public static int getRemainDays(Date future) {
		return (int) ((future.getTime() - new Date().getTime())/millSecondsPerDay);
	}
	
	/**
	 * 判断是否为当天
	 * @param future
	 * @return 返回年龄
	 */
	public static boolean isToday(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String format = fmt.format(date);
		String format2 = fmt.format(new Date());
		return format.equals(format2);
	}
	
	/**
	 * 获取当月月初时间
	 * @param date
	 * @return 
	 */
	public static Date isBeginMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 0);//设置零小时
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取当月月末的时间
	 * @param date
	 * @return 
	 */
	public static Date isEndMonth() {
		//获取日历的实例
		Calendar calendar = Calendar.getInstance();
		//设置为当前的时间
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);//月份加1
		//下列代码设置为月初
		calendar.set(Calendar.SECOND, 0);//设置分钟为0
		calendar.set(Calendar.HOUR, 0);//设置小时为0
		calendar.set(Calendar.MINUTE, 0);//设置秒为0
		calendar.set(Calendar.AM_PM, Calendar.AM);//设置时间为上午
		calendar.set(Calendar.DATE, 1);//设置为1日
		calendar.add(Calendar.SECOND, -1);//设置秒为-1 等于减少1秒
		return calendar.getTime();
	}

}

/**
 * 
 */
package yuanbaowang_cms_utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 袁保旺
 *
 * 2019年12月5日 下午1:19:28 
 */
public  class StringUtils {
	static Random random = new Random();
	static char charArray[] = new char[36];
	
	/**
	 * html 字符的转义处理
	 * @param str
	 * @return
	 */
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	/**
	 * 随机字符串源
	 */
	static {
		
		for (int i = 0; i < 18; i++) {
			charArray[i] = (char) ('0'+random.nextInt(10));
		}
		for (int i = 0; i < 18; i++) {
			charArray[i+18] = (char)('A'+i);
		}
	}
	
	
	
	
	/**
	 * 判断一个字符串是否为空，空字符串也默认为空
	 * @param str
	 * @return 为空返回true，否则返回false
	 * 
	 */
	public static boolean isBlank(String str) {
		return null == str || "".equals(str.trim());
	}
	
	
	/**
	 * 判断一个字符串是否有值
	 * @param str
	 * @return 有值返回true，否则返回false
	 * 
	 */
	public static boolean haveValue(String str) {
		return !(null == str || "".equals(str.trim()));
	}
	
	
	/**
	 * 判断一个字符串是否为数字
	 * @param str
	 * @return 为空返回true，否则返回false
	 * 
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	
	
	/**
	 * 验证是否为规范手机号
	 * @param str
	 * @return 如果通过返回true  失败返回false
	 * 
	 */
	public static boolean isMobile(String str) {
		String regex = "^1[3578]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	/**
	 * 验证长度为n的英文字符
	 * @param str
	 * @return 返回添加好的字符串
	 * 
	 */
	public static String getRandonStr(int n) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char nextInt = (char) ('A'+random.nextInt(26));
			str.append(nextInt);
		}
		return str.toString();
	}
	
	/**
	 * 验证长度为n的英文和数字字符串
	 * @param str
	 * @return 返回添加好的字符串
	 * 
	 */
	public static String getRandomAndNumStr(int n) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(36);
			char randomChar = charArray[index];
			str.append(randomChar);
		}
		return str.toString();
	}
	
	/**
	 * 获取一个中文汉字
	 * @param str
	 * @return 返回添加好的字符串
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public static String getChaines() throws UnsupportedEncodingException {
		 byte word[] = new byte[2];
		 word[0] = (byte) (0xA1 + 16 + random.nextInt(39));
		 word[1] = (byte) (0xA1 + random.nextInt(44));
		 return new String(word,"GBK");
	}
	
	/**
	 * 循环输出多个汉字字符串
	 * @param str
	 * @return 返回添加好的字符串
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public static String forGetChaines(int n) throws UnsupportedEncodingException {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String string = StringUtils.getChaines();
			str.append(string);
		}
		return str.toString();
	}
	
	/**	
	 * 验证邮箱
	 */
	public static boolean isEmail(String email) {
		String regex = "^\\w+@\\w+\\.[a-zA-Z]{2,3}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(email);
		return matcher.matches();
	}
	

}

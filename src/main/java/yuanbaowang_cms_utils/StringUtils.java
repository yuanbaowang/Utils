/**
 * 
 */
package yuanbaowang_cms_utils;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
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
	 * 判断是否为合法 url
	 * @param str
	 * @return 为空返回true，否则返回false
	 * 
	 */
	public static boolean isUrl(String url) {
		 //转换为小写
        String str = url.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
               + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184               
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "[0-9a-z]*"  // 或单域名
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
        return  str.matches(regex);
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
		String regex = "^\\d+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	
	/**
	 * 判断一个字符串是否为数字或小数
	 * @param str
	 * @return 为空返回true，否则返回false
	 * 
	 */
	public static boolean isNumberAndDouble(String str) {
		String regex = "^[0-9]+(\\.[0-9]+)?$";
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
	
	/**
	 * 	随机生成姓名
	 */
	public static String getName() {
		String name = "";
		for (int i = 0; i < 3; i++) {
			char c = (char) (0x4e00 + (int) (Math.random()*(0x9fa5 - 0x4e00 + 1)));
			name += c;
		}
		return name;
	}
	
	
	/**
	 * 	随机生成性别
	 */
	public static String getSex() {
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 0) {
			return "男";
		}else {
			return "女";
		}
	}
	
	
	 //随机生成生日
    public static String getBirthday(){
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1949, 2001);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
    //随机生成邮箱
    public static String getMail(){
        String [] last = {"@qq.com", "@gmail.com", "@163.com", "@sina.com", "@hotmail.com", "@sohu.com"};
        StringBuffer sb = new StringBuffer();
        // 3~20长度，包含3及20
        int length = 3 + (int) (Math.random() * 9);
        String word = "";
        for (int i = 0; i < length; i++) {
            word += (char) randomChar();
        }
        sb.append(word);
        Random r = new Random();
        final int j = r.nextInt(6);
        sb.append(last[j]);
        return sb.toString();
    }
    
    /**
     * 	随机生成字符
     */
    public static byte randomChar() {
        // 0<= Math.random()< 1
        int flag = (int) (Math.random() * 2);// 0小写字母1大写字母
        byte resultBt;
        if (flag == 0) {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (65 + bt);
        } else {
            byte bt = (byte) (Math.random() * 26);// 0 <= bt < 26
            resultBt = (byte) (97 + bt);
        }
        return resultBt;
    }
	
    
    //获取随机数
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    
    /**
     * 	随机生成手机号码
     */
    private static String[] telFirst = "133".split(",");
    public static String getPhone(){
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+thrid;

    }
	

}

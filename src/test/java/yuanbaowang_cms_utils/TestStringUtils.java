/**
 * 
 */
package yuanbaowang_cms_utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 袁保旺
 *
 * 2019年12月5日 下午1:26:10 
 */
public class TestStringUtils {
	static StringUtils stringUtils;
	@Before
	public void init() {
		System.out.println("init Before");
		stringUtils = new StringUtils();
	}
	
	@BeforeClass
	public static void init1() {
		System.out.println("init BeforeClass");
		stringUtils = new StringUtils();
	}
	
	//判断是否为空字符串1
	@Test	
	public void testIsBlank() {
		boolean blank = stringUtils.isBlank("");
		System.out.println(blank);
		//验证返回是否为真
		Assert.assertTrue(blank == true);
	}
	
	//判断是否为空字符串2
	@Test	
	public void testIsBlank2() {
		boolean blank = stringUtils.isBlank("");
		System.out.println(blank);
		//验证返回是否为假
		Assert.assertFalse(blank == false);
	}
	
	//判断是否有值
	@Test	
	public void testHaveValue() {
		boolean blank = stringUtils.haveValue("11");
		System.out.println(blank);
		//验证返回是否为真
		Assert.assertTrue(blank == true);
	}
	
	//验证手机号
	@Test	
	public void testIsMobile() {
		String str = "15033739973";
		boolean blank = stringUtils.isMobile(str);
		System.out.println(blank);
		//验证返回是否为真
		Assert.assertTrue("正确",blank == true);
	}
	
	//测试随机26个字母
	@Test	
	public void testGetRandonStr() {
		String blank = stringUtils.getRandonStr(20);
		System.out.println(blank);
		//验证返回是否为真
		Assert.assertTrue(20 == blank.length());
	}
	
	//测试随机36个字母和数字
	@Test	
	public void testGetRandomAndNumStr() {
		String blank = stringUtils.getRandomAndNumStr(20);
		System.out.println(blank);
		//验证返回是否为真
		Assert.assertTrue(20 == blank.length());
	}
	
	//测试随机中文
	@Test	
	public void testForGetChaines() throws UnsupportedEncodingException {
		String blank = stringUtils.forGetChaines(20);
		System.out.println(blank);
	}
	
	//测试年龄
	@Test	
	public void testGetAge() {
		Date date = new Date(100,0,3);
		int age = DateUtils.getAge(date);
		System.out.println(age);
	}
	
	//测试剩余天数
	@Test	
	public void testRemainDays() {
		Date date = new Date(120,0,13);
		int age = DateUtils.getRemainDays(date);
		System.out.println(age);
	}
	
	//测试是否为当天
	@Test	
	public void testIsToday() {
		Date date = new Date(120,0,1);
		boolean age = DateUtils.isToday(date);
		System.out.println(age);
		boolean age1 = DateUtils.isToday(new Date());
		System.out.println(age1);
	}
	
	//测试获取当月
	@Test	
	public void testIsBeginMonth() {
		Date beginMonth = DateUtils.isBeginMonth();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
		String format = fmt.format(beginMonth);
		System.out.println(format);
	}
	
	//测试获取当月月末时间
	@Test	
	public void testIsEndMonth() {
		Date beginMonth = DateUtils.isEndMonth();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
		String format = fmt.format(beginMonth);
		System.out.println(format);
	}
	
	//测试获取文件后缀名
	@Test	
	public void testSuffixName() {
		String suffixName = FileUtils.getSuffixName("abc.txt");
		System.out.println(suffixName);
	}
	
	//测试循环删除文件和目录
	@Test	
	public void testDelFile() {
		FileUtils.delFile("D:\\day");
	}
	
	//测试循环删除文件和目录
	@Test	
	public void testGetProperty() {
		String property = FileUtils.getProperty("file.separator");
		System.out.println(property);
	}
	
	//测试循环删除文件和目录
	@Test	
	public void testGetEnv() {
		String property = FileUtils.getEnv("JAVA_HOME");
		System.out.println(property);
	}
	
	//测试循环删除文件和目录
	@Test	
	public void testGetFileSize() {
		long fileSize = FileUtils.getFileSize("D:\\day01\\a.txt");
		System.out.println(fileSize);
	}
	
	//测试文件是否一致
	@Test	
	public void testGetFileChange() throws FileNotFoundException, IOException {
		String fileChange = FileUtils.getFileChange("D:\\003Javaweb\\23", "D:\\003Javaweb\\231");
		System.out.println(fileChange);
	}
	
	//测试邮箱是否正确
	@Test	
	public void testIsEmail() {
		boolean email = StringUtils.isEmail("2637562166@qq.com");
		System.out.println(email);
	}
	
	//测试邮箱是否正确
	@Test	
	public void testReadFile() throws IOException {
		List<String> readFile = FileUtils.readFile("D:\\day01//a.txt");
		System.out.println(readFile);
	}
	
	//测试复制文件
	@Test	
	public void testCopy() throws IOException {
		FileUtils.copy("D:\\day01//a.txt","D:\\\\day01//b.txt");
	}

}

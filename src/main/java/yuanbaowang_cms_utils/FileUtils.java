/**
 * 
 */
package yuanbaowang_cms_utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 袁保旺
 *
 * 2019年12月6日 下午7:57:40 
 */
public class FileUtils {
	
	/**
	 * 	获取所有文件的扩展名
	 */
	public static String getSuffixName(String fileName) {
		//获取最后一个点的位置
		int dotPos = fileName.lastIndexOf('.');
		if(dotPos < 0) {
			return "";
		}
		//如果逗号后有内容  就返回从点开始的下标开始截取
		return fileName.substring(dotPos);
	}
	
	/**
	 * 删除文件
	 */
	public static void delFile(String fileName) {
		File file = new File(fileName);
		//文件不存在
		if(!file.exists()) {
			return;
		}
		//判断是否为目录
		if(file.isDirectory()) {
			//不为空就获取所有的文件
			String[] list = file.list();
			for (String s : list) {
				String childFileName = fileName+"/"+s;
				delFile(childFileName);
			}
		}
		//如果是文件 或 子目录 就删除
		file.delete();
	}
	
	
	/**	
	 * 根据属性值  获取系统环境变量
	 */
	public static String getProperty(String key) {
		Properties properties = System.getProperties();
		
		return properties.getProperty(key);
	}
	
	/**	
	 * 根据属性值  获取系统环境变量
	 */
	public static String getEnv(String key) {
		Map<String, String> properties = System.getenv();
		return properties.get(key);
	}
	
	/**	
	 * 根据文件  获取文件大小
	 */
	public static long getFileSize(String fileName) {
		File file = new File(fileName);
		if(!file.exists() || !file.isFile()) {
			return 0;
		}
		return file.length();
	}
	
	
	
	/**	
	 * 比较两个文件夹中的内容是否一致
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String getFileChange(String src,String dst) throws FileNotFoundException, IOException {
		File srcFile = new File(src);//源目录
		File dstFile = new File(dst);//目标目录
		//判断源文件是否存在
		if(!srcFile.exists()) {
			System.out.println( "源文件不存在"+src );
			return "源文件不存在"+src;
		}
		
		//判断源文件是否存在
		if(!dstFile.exists()) {
			System.out.println( "目标文件不存在"+dst );
			return "目标文件不存在"+dst;
		}
		
		if(srcFile.isFile() && dstFile.isFile()) {
			if(srcFile.length() != dstFile.length()) {
				System.out.println( "文件大小不一致！"+src );
				return "文件大小不一致！"+src;
			}else {
				//获取文件hash值  进行比较
				byte[] md5Src = DigestUtils.md5(new FileInputStream(srcFile));
				byte[] md5Dst = DigestUtils.md5(new FileInputStream(dstFile));
				String strMd5Src = new String(md5Src);
				String strMd5Dst = new String(md5Dst);
				if(!strMd5Src.equals(strMd5Dst)) {
					System.out.println("文件内容不一致"+src );
					return "文件内容不一致"+src;
				}
			}
		}
		if(srcFile.isDirectory()) {
			String[] list = srcFile.list();
			for (String str : list) {
				String childSrcFile = src +"\\"+str;
				String childDstFile = dst +"\\"+str;
				getFileChange(childSrcFile,childDstFile);
			}
		}
		return "";
		
	}
	
	/**
	 * @throws IOException 
	 *	读取文件
	 * 
	 */
	public static List<String> readFile(String fileName) throws IOException {
		List<String> list = new ArrayList<String>();
		File file = new File(fileName);
		//new 输入流
		FileInputStream fileInputStream = new FileInputStream(file);
		//new 缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream,"utf-8"));
		String line = "";
		//按行读取
		while((line = br.readLine()) != null) {
			list.add(line);
		}
		//释放资源
		closeFile(br,fileInputStream);
		return list;
	}
	
	/**
	 * 释放资源方法
	 * @throws IOException 
	 */
	public static void closeFile(Closeable ...more) throws IOException {
		for (int i = 0; i < more.length; i++) {
			more[i].close();
		}
	}
	
	/**
	 * 复制文件
	 * @throws IOException 、
	 * src 源文件
	 * dst 目标文件
	 */
	public static void copy(String srcFileName,String dstFileName) throws IOException {
		//源文件
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		
		//新建输入流 和 输出流
		FileInputStream inpt = new FileInputStream(srcFile);
		FileOutputStream oup = new FileOutputStream(dstFile);
		//按字节读取
		byte[] b = new byte[1024];
		while(inpt.read(b) > 0) {
			oup.write(b);
		}
		//释放资源
		closeFile(oup,inpt);
	}
	
	/**
	 * @Title: writeFile
	 * @Description: 按照指定的编码把内容写入指定的文件中
	 * @param path
	 * @param content
	 * @param charset
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 判断父目录是否存在
		if (!file.getParentFile().exists()) {
			// 创建父目录
			file.getParentFile().mkdirs();
		}
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		if(content!=null) {
			bw.write(content+"\n");
		}
		bw.flush();
		bw.close();
	}

	
	/**
	 * 	下载图片
	 * @throws IOException 
	 */
	/*
	 * @RequestMapping("downLoad") public void downLoad(HttpServletResponse
	 * response,String filename) throws IOException { //读取文件 InputStream input = new
	 * FileInputStream("d:\\pic\\"+filename); //设置输出格式 response.reset();
	 * response.setContentType("bin"); response.addHeader("Content-Disposition",
	 * "attachment;filename=\""+filename+"\"");
	 * 
	 * //循环取出流中数据 byte[] b = new byte[1024]; int len;
	 * 
	 * try { while((len = input.read(b)) > 0) {
	 * response.getOutputStream().write(b,0,len);
	 * 
	 * } } catch (Exception e) { input.close(); } }
	 */
	
	/**
	 * 	进行处理上传文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	/*
	 * private String processFile(MultipartFile file) throws IllegalStateException,
	 * IOException {
	 * 
	 * if(file.isEmpty()) { return ""; } //获取当前日期 SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyyMMdd"); String subPath = sdf.format(new Date());
	 * //图片存放的路径 File path = new File("d://pic//"+subPath); //判断目标目录时间是否存在
	 * if(!path.exists()) { //路径不存在创建 path.mkdirs(); } //计算文件的扩展名 String suffixName
	 * = FileUtils.getSuffixName(file.getOriginalFilename()); //使用UUid生成随机文件名 String
	 * fileName = UUID.randomUUID().toString()+suffixName; //把文件另存到新的文件夹中
	 * file.transferTo(new File(path+"/"+fileName)); return subPath+"/"+fileName; }
	 */
	
	
	
	

}

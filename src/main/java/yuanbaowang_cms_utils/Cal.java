/**
 * 
 */
package yuanbaowang_cms_utils;

import java.util.Calendar;
import java.util.Iterator;

/**
 * @author 袁保旺
 *
 * 2019年12月24日 下午8:56:25 
 */
public class Cal {
	
	/**
	 * 	直接调用此方法 计算经纬度
	 */
	public static double calBylw(double startl,double startw, double endl,double endw) {
		return getDistance(startl,startw,endl,endw);
	}
	
	/**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;
 
    /**
      * @description 根据经纬度，计算两点间的距离
      * @param longitude1 第一个点的经度
      * @param latitude1  第一个点的纬度
      * @param longitude2 第二个点的经度
      * @param latitude2  第二个点的纬度
      * @return 返回距离 单位千米
    
      */
    
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * EARTH_RADIUS * 1000;
        return s;
    }
    
    
    /**
     * 	月考计算经纬度工具类
     */
    public static int getKm(double longitude, double latitude) {
    	double a =  Math.round( Math.sqrt( Math.pow(longitude - 39 ,2  ) +  Math.pow(longitude - 116 ,2  ) ));
    	if(a >= 0 && a <= 15) {
    		return 2;
    	}
    	
    	if(a >= 16 && a <= 30) {
    		return 3;
    	}
    	
    	if(a >= 31 && a <= 40) {
    		return 4;
    	}
    	
    	if(a >= 41 && a <= 60) {
    		return 5;
    	}else {
    		return 6;
    	}
    	
    }
    
    /**
     * 	月考计算是否违规工具类
     * 	str 代表时间  car代表车类型，km代表距离 carId,汽车牌照
     */
    public static int getRule(String str,String car,String carId,int km) {
    	if(car.equals("A") && km < 15) {
    		return 0;//"摩托车A进入2环"
    	}
    	
    	if(car.equals("B") && km < 40) {
    		return 1;//"摩托车B进入4环"
    	}
    	
    	if(car.equals("C") && km >15 && !carId.substring(0,1).equals("京") && km < 60) {
    		return 2;//"外地牌照不能进入5环"
    	}
    	
    	return 3;//咩问题
    }
    
    /**
     * 	单双号限行
     */
    public static int getSingleDouble(String str,String car,String carId,int km) {
    	if(car.equals("C") && km >15 && carId.substring(0,1).equals("京")) {
    		//计算车牌号最后一位数字
    		String substring = str.substring(carId.length()-1,carId.length());
    		int sub = Integer.valueOf(substring);
    		//找天数
    		String substring2 = str.substring(8,10);
    		int sub2 = Integer.valueOf(substring2);
    		
    		if(sub2 % sub != 0) {
    			return 1;
    		}
    	}
    	return 0;
    }

}

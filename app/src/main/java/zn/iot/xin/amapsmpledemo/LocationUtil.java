package zn.iot.xin.amapsmpledemo;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dim on 2017/8/18.
 */

public class LocationUtil {
    private AMapLocation amapLocation;
    private AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

    public boolean autoStart(){
        setHightAccuracyMode();
        setOnceLocation(true);//默认为取一次位置
        setOnceLocationLatest(false);//不使用三次中最精确值
        //setInterval(2000);
        setNeedAddress(true);
        setWifiActiveScan(false);
        setNeedAddress(true);
        setHttpTimeOut(30000);
        setLocationCacheEnable(false);
        return true;
    }
    public boolean autoStart(AMapLocationClientOption.AMapLocationMode mode,boolean once,boolean latest,
                             boolean add,boolean scan,int timeout,boolean cache){
        mLocationOption.setLocationMode(mode);
        if(latest) {
            setOnceLocation(latest);//默认为取一次位置
            setOnceLocationLatest(latest);//不使用三次中最精确值
        }else{
            setOnceLocation(once);// 设置是否单次定位//默认为取一次位置
        }
        setNeedAddress(add);
        setWifiActiveScan(scan);
        setHttpTimeOut(timeout);
        setLocationCacheEnable(cache);
        return true;
    }
    public boolean autoStart(AMapLocationClientOption.AMapLocationMode mode,boolean once,boolean latest,
                             int time,boolean add,boolean scan,int timeout,boolean cache){
        mLocationOption.setLocationMode(mode);
        if(latest) {
            setOnceLocation(latest);//默认为取一次位置
            setOnceLocationLatest(latest);//不使用三次中最精确值
        }else{
            setOnceLocation(once);// 设置是否单次定位//默认为取一次位置
        }
        setInterval(time);
        setNeedAddress(add);
        setWifiActiveScan(scan);
        setHttpTimeOut(timeout);
        setLocationCacheEnable(cache);
        return true;
    }

    public void setAmapLocation(AMapLocation amapLocation) {
        this.amapLocation = amapLocation;
    }

    public AMapLocation getAmapLocation() {
        return amapLocation;
    }

    public void setHightAccuracyMode() {
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    }

    public void setBatterySavingMode() {
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
    }

    public void setDeviceSensorsMode() {//设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
    }

    public void setOnceLocation(boolean is) {
        //获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(is);
    }

    public void setOnceLocationLatest(boolean is) {
        if (is) {
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);
            setOnceLocation(true);
        } else {
            mLocationOption.setOnceLocationLatest(false);
        }
    }

    public void setInterval(int val) {
        //        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption.setInterval(1000);
        if (val < 1000)
            val = 1000;
        mLocationOption.setInterval(val);
    }

    public void setNeedAddress(boolean b) {
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(b);
    }

    public void setWifiActiveScan(boolean b) {
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(b);
    }

    public void setMockEnable(boolean b) {
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(b);
    }

    public void setHttpTimeOut(int time) {
    //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
    if(time<8000){
        time=8000;
    }
        mLocationOption.setHttpTimeOut(time);
    }
    public void setLocationCacheEnable(boolean b){
//关闭缓存机制
        mLocationOption.setLocationCacheEnable(b);
    }

    /**
     * 检查定位是否成功
     * @return
     */
    public int onSuccess(){
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                return 0;
            }
        }
        return getErrorCode();
    }

    public int getErrorCode(){
        return amapLocation.getErrorCode();
    }
    public String getErrorInfo(){
        return amapLocation.getErrorInfo();
    }

    public String getDate(){//获取定位时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(amapLocation.getTime());
        return df.format(date).toString();
    }


    public String getLocationType()//获取当前定位结果来源，如网络定位结果，详见定位类型表
    {
        return String.valueOf(amapLocation.getLocationType()) ;
    }

    public String getLatitude() //获取纬度
    {
        return String.valueOf(amapLocation.getLatitude());
    }
    public String getLongitude() //获取经度
    {
        return String.valueOf(amapLocation.getLongitude());
    }
    public String getAccuracy() //获取精度信息
    {
        return String.valueOf(amapLocation.getAccuracy());
    }
    public String getAddress() //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    {
        return amapLocation.getAddress();
    }
    public String getCountry() //国家信息
    {
        return amapLocation.getCountry();
    }
    public String getProvince() //省信息
    {
        return amapLocation.getProvince();
    }
    public String getCity() //城市信息
    {
        return amapLocation.getCity();
    }
    public String getDistrict() //城区信息
    {
        return amapLocation.getDistrict();
    }
    public String getStreet() //街道信息
    {
        return amapLocation.getStreet();
    }
    public String getStreetNum() //街道门牌号信息
    {
        return amapLocation.getStreetNum();
    }
    public String getCityCode() //城市编码
    {
        return amapLocation.getCityCode();
    }
    public String getAdCode() //地区编码
    {
        return amapLocation.getAdCode();
    }
    public String getAoiName() //获取当前定位点的AOI信息
    {
        return amapLocation.getAoiName();
    }
    public String getBuildingId() //获取当前室内定位的建筑物Id
    {
        return amapLocation.getBuildingId();
    }
    public String getFloor() //获取当前室内定位的楼层
    {
        return amapLocation.getFloor();
    }


    public AMapLocationClientOption getmLocationOption() {
        return mLocationOption;
    }

    public void setmLocationOption(AMapLocationClientOption mLocationOption) {
        this.mLocationOption = mLocationOption;
    }



    public String getErrorCodeAndInfo() {
        return "errCode:"+getErrorCode()+",errorInfo:"+getErrorInfo();//定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
    }

    public String getInfoString() {
        return getCountry()+getProvince()+getCity()+getDistrict()+getStreet()+getStreetNum();
    }
    public String getLatAndLong(String pat){
        if(pat!=null)
            return getLatitude()+"pat"+getLongitude();
        else
            return getLatitude()+","+getLongitude();
    }
}

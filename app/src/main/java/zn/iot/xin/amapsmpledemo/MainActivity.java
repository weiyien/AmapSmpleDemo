package zn.iot.xin.amapsmpledemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends AppCompatActivity {
    private TextView hello;
    private LocationUtil locationUtil;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            locationUtil.setAmapLocation(amapLocation);
            int result=locationUtil.onSuccess();//获取返回信息代码
            if(result==0){
                //获取国家省份....街道号信息
                Toast.makeText(getApplicationContext(),locationUtil.getInfoString(),Toast.LENGTH_SHORT).show();
                hello.setText(locationUtil.getInfoString());
                stopLocation();
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Toast.makeText(getApplicationContext(),locationUtil.getErrorCodeAndInfo(),Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello= (TextView) findViewById(R.id.hello);
        locationUtil=new LocationUtil();
        locationUtil.autoStart();
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.setLocationOption(locationUtil.getmLocationOption());

        startLocation();
    }
    public void startLocation(){
        mLocationClient.startLocation();//启动定位
    }
    public void stopLocation(){
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }
}

# 高德SDK使用

# 1.环境(显示地图)
- 获取高德Key
- 引入jar和so库文件
- 配置Key及基本权限到 AndroidManifest.xml 文件
- 布局中添加MapView控件
- mapView.onCreate(savedInstanceState);

# 2.显示定位蓝点
- aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置
- aMap.setMyLocationEnabled(true); //设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false
- aMap.setLocationSource(this);// 设置定位监听

# 3.显示室内地图
- showIndoorMap(boolean enable)     //true：显示室内地图；false：不显示；

# 4.切换地图图层
- aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式

> MAP_TYPE_NAVI 导航地图

> MAP_TYPE_NIGHT 夜景地图

> MAP_TYPE_NORMAL 白昼地图（即普通地图）

> MAP_TYPE_SATELLITE 卫星图

# 5.离线地图
- 可以根据城市编码和城市名称两种方式下载当前城市的离线地图

```
//构造OfflineMapManager对象 
OfflineMapManager amapManager = new OfflineMapManager(this, this);
//按照citycode下载
amapManager.downloadByCityCode(String citycode)；
//按照cityname下载
amapManager.downloadByCityName(String cityname)；
```

# 6.控件交互
- setZoomControlsEnabled(boolean b);// 缩放按钮
- setCompassEnabled(boolean b); //指南针
- setScaleControlsEnabled(boolean b);//比例尺控件

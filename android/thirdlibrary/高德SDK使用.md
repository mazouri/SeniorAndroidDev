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

# 7.手势交互
- UiSettings.setAllGesturesEnabled (boolean）//所有手势

# 8.方法交互
- 改变地图的中心点 CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(39.977290,116.337000),18,30,0));
- 改变地图的缩放级别 CameraUpdateFactory.zoomTo(17);
- 限制地图的显示范围 new LatLngBounds(southwestLatLng, northeastLatLng);aMap.setMapStatusLimits(latLngBounds);

# 9.对地图进行截屏
- aMap.getMapScreenShot{onMapScreenShot(Bitmap bitmap, int status)}

# 10.绘制点标记
- aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));
- 定义 Marker 点击事件监听: mAMap.setOnMarkerClickListener(markerClickListener);
- Marker 拖拽事件: aMap.setOnMarkerDragListener(markerDragListener);
- 绘制 InfoWindow: 调用Marker 类的 showInfoWindow() 和 hideInfoWindow() 方法可以控制显示和隐藏
- 可触发的 InfoWindow 事件:aMap.setOnInfoWindowClickListener(onInfoWindowClickListener);

# 11.绘制线
- AMap.addPolyline(new PolylineOptions().addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
# 12.绘制面
- 绘制圆:AMap.addCircle
- 绘制多边形:aMap.addPolygon
- 绘制长方形:
```
/**
   * 生成一个长方形的四个坐标点
   */
  private List<LatLng> createRectangle(LatLng center, double halfWidth,
      double halfHeight) {
    List<LatLng> latLngs = new ArrayList<LatLng>();
    latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude + halfWidth));
    latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude + halfWidth));
    latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude - halfWidth));
    return latLngs;
  }
}

```
```
// 绘制一个长方形
aMap.addPolygon(new PolygonOptions()
.addAll(createRectangle(Constants.SHANGHAI, 1, 1))
.fillColor(Color.LTGRAY).strokeColor(Color.RED).strokeWidth(1));
```
# 13.绘制热力图
- 热力图功能提供将自有数据展示在地图上，可以给使用者直观描述一个区域的人员，车辆等事物的热度情况
- HeatmapTileProvider 是生成热力图的核心类;通过 TileOverlay 绘制热力图
```
// 初始化 TileOverlayOptions
TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
tileOverlayOptions.tileProvider(heatmapTileProvider); // 设置瓦片图层的提供者 
// 向地图上添加 TileOverlayOptions 类对象
mAMap.addTileOverlay(tileOverlayOptions);

```
# 14.绘制3D模型
- 使用OpenGL绘制

# 15.轨迹纠偏
- 轨迹纠偏可帮助您将您记录的行车轨迹点进行抽稀、纠偏操作，将轨迹匹配到道路上，提供平滑的绘制效果，并计算行驶里程
- LBSTraceClient
- 需要按照 TraceLocation 定义好的格式构造轨迹点 List:必须有经纬度信息
- 轨迹纠偏支持传入多种坐标系（高德、GPS原始坐标以及百度）的轨迹点数据，并且可支持多条数据同时纠偏。
```
mTraceClient.queryProcessedTrace(mSequenceLineID, mTraceList,
                mCoordinateType, this);
```
- 获取纠偏后的数据:实现 TraceListener 监听器获取到`List<LatLng> linepoints`及轨迹的总距离

# 16.点平滑移动
- 根据输入的关键点和时间参数，实现点的平滑移动效果
- 使用场景：可应用到展示车辆行驶轨迹、用户移动轨迹等场景
- startSmoothMove()

# 17.绘制海量点图层
- 设置海量点属性:MultiPointOverlayOptions
- 添加海量点获取管理对象:aMap.addMultiPointOverlay(overlayOptions); 
- 读取数据并通过海量点管理对象设置:multiPointOverlay.setItems(list);
- 海量点点击事件aMap.setOnMultiPointClickListener(multiPointClickListener);

# 18.获取POI数据
- POI:兴趣点.在地图表达中，一个 POI 可代表一栋大厦、一家商铺、一处景点等等。通过POI搜索，完成找餐馆、找景点、找厕所等等的功能
## 关键字检索POI:
- 继承 OnPoiSearchListener 监听;
- 构造 PoiSearch.Query 对象，通过 PoiSearch.Query(String query, String ctgr, String city) 设置搜索条件
- 构造 PoiSearch 对象，并设置监听`poiSearch.setOnPoiSearchListener(this)`
- 调用 PoiSearch 的 searchPOIAsyn() 方法发送请求:poiSearch.searchPOIAsyn();
- 通过回调接口 onPoiSearched 解析返回的结果:result.getPois()可以获取到PoiItem列表
## 周边检索POI
- 与关键字检索的唯一区别需要通过 PoiSearch 的 setBound 方法设置圆形查询范围
```
poiSearch.setBound(new SearchBound(new LatLonPoint(locationMarker.getPosition().latitude,
            locationMarker.getPosition().longitude), 1000));//设置周边搜索的中心点以及半径
```
## 多边形内检索的POI
```
List<LatLonPoint> points = new ArrayList<LatLonPoint>();
points.add(new LatLonPoint(39.941711, 116.382248));
points.add(new LatLonPoint(39.884882, 116.359566));
points.add(new LatLonPoint(39.878120, 116.437630));
points.add(new LatLonPoint(39.941711, 116.382248));

poiSearch.setBound(new SearchBound(points));//设置多边形区域
```
### 输入内容自动提示
- 输入提示是指根据用户输入的关键词，给出相应的提示信息，将最有可能的搜索词呈现给用户，以减少用户输入信息，提升用户体验
- 继承 InputtipsListener 监听。
- 构造 InputtipsQuery 对象,通过 InputtipsQuery(java.lang.String keyword, java.lang.String city) 设置搜索条件
- 构造 Inputtips 对象，并设置监听
```
Inputtips inputTips = new Inputtips(InputtipsActivity.this, inputquery);
inputTips.setInputtipsListener(this);
```
- 调用 PoiSearch 的 requestInputtipsAsyn() 方法发送请求

### 道路沿途检索POI
- 从搜索 SDK 3.5.0 版本开始支持，可查询路径沿途的加油站、ATM、汽修店、厕所
- RoutePOISearchQuery
- RoutePOISearch.searchRoutePOIAsyn();

# 19.获取地址描述数据
## 地理编码（地址转坐标）
- 继承 OnGeocodeSearchListener 监听
- 构造 GeocodeSearch 对象，并设置监听
- GeocodeQuery设置查询参数
- 调用 GeocodeSearch 的 getFromLocationNameAsyn(GeocodeQuery geocodeQuery) 方法发起请求
- 通过回调接口 onGeocodeSearched 解析返回的结果
## 逆地理编码（坐标转地址）
- 继承 OnGeocodeSearchListener 监听
- 构造 GeocodeSearch 对象，并设置监听
- 通过 RegeocodeQuery设置查询参数
- 调用 GeocodeSearch 的 getFromLocationAsyn(RegeocodeQuery regeocodeQuery) 方法发起请求
- 通过回调接口 onRegeocodeSearched 解析返回的结果

# 20.获取公交数据
## 公交站点查询
- BusStationQuery
- busStationSearch.searchBusStationAsyn
## 公交路线查询
- 设置查询条件BusLineSearch
- BusLineSearch.searchBusLineAsyn()发送请求和接收数据

# 21.驾车出行路线规划
- RouteSearch
- DriveRouteQuery设置搜索参数
- RouteSearch 的 calculateDriveRouteAsyn

# 22.步行出行路线规划
- WalkRouteQuery
- RouteSearch 的 calculateWalkRouteAsyn

# 23.公交出行路线规划
- RouteSearch
- BusRouteQuery
- RouteSearch 的 calculateBusRouteAsyn
# 24.骑行出行路线规划
- RouteSearch
- RideRouteQuery
- RouteSearch 的 calculateRideRouteAsyn

# 24.坐标转换
- 支持GPS/Mapbar/Baidu等多种类型坐标在高德地图上使用;参见类CoordinateConverter
- 经纬度坐标与屏幕像素坐标互转
- fromScreenLocation:根据转入的屏幕位置返回一个地图位置（经纬度）
- toScreenLocation:返回一个从地图位置转换来的屏幕位置

# 25.距离/面积计算
## 两点间的直线距离计算
```
AMapUtils.calculateLineDistance(latLng1,latLng2);
```
## 面积计算
```
AMapUtils.calculateArea(leftTopLatlng,rightBottomLatlng)
```

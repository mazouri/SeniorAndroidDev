# 计算Bitmap占内存大小
http://www.cnblogs.com/zj2012zy/p/5331302.html

http://blog.csdn.net/hudashi/article/details/7856519

## Android 各文件夹
<img src="http://upload-images.jianshu.io/upload_images/607813-a8d87b90116a9ba8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240&_=5331302">
## 当图片以格式ARGB_8888存储时的计算方式
> 占用内存=图片长*图片宽*4字节
> 图片长 = 图片原始长 * (设备DPI/文件夹DPI) 
> 图片宽 = 图片原始宽 * (设备DPI/文件夹DPI) 

## 举例
> 图片大小 200 * 320，设备为红米dpi为320，属于xhdpi设备

验证一 图片放在**hdpi**，下面为代码输出结果：
```
DD/MainActivity(13014): dpi: 320    bitmap ByteCount: 456036
```
图片长 = （320 / 240） * 200  = 266.67

图片宽 = （320 / 240 ）* 320 = 426.67

占用内存 = 266.67 * 426.67 * 4 = 455116 与 实际值大致相同

验证二：图片放xxhdpi下，下面为代码输出结果：
```
D/MainActivity(13014): dpi: 320    bitmap ByteCount: 113316
```
图片长 = （320 / 480 ） * 200 = 133.33

图片宽 = （320 / 480 ） * 320 = 213.33

占用内存 = 133.33 * 213.33 * 4 = 113774 与 实际值大致相同。












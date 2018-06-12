## 1.Android Studio的图片到底是放在drawable还是mipmap呢?
链接：[Android Studio的图片到底是放在drawable还是mipmap呢](https://www.jianshu.com/p/991c29a5e2b3)

简答：图片应该放在drawable文件夹下，而mipmap文件夹只适合放app icons ，之前Android Studio 1.1版本的时候app icons上上传几个不同分辨率的图片，而现在Android Studio 2.1.2 已经把mipmap文件夹默认分为了不同分辨率的文件夹，方便适配。

## Android N(7.0)安装包安装方式变更
```
    /**
     * install app
     *
     * @param context
     * @param filePath
     * @return whether apk exist
     */
    public static boolean install(Context context, String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            File file = new File(filePath);
            if (file.length() > 0 && file.exists() && file.isFile()) {
                Uri contentUri = FileProvider.getUriForFile(context,
                        context.getPackageName() + ".fileprovider", file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            }
        } else {
            File file = new File(filePath);
            if (file.length() > 0 && file.exists() && file.isFile()) {
                Uri path = Uri.parse(filePath);
                if (path.getScheme() == null) {
                    path = Uri.fromFile(file);
                }
                intent.setDataAndType(path, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            }
        }
        return false;
    }
```

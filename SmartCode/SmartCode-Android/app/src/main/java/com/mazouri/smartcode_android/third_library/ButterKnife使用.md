＃ Butter Knife使用

## 链接：
[http://jakewharton.github.io/butterknife/](http://jakewharton.github.io/butterknife/)

## 添加库
root build.gradle:
```
buildscript {
  repositories {
    mavenCentral()
   }
  dependencies {
    classpath 'com.jakewharton:butterknife-gradle-plugin:8.7.0'
  }
}
```
apply it in your app or library build.gradle:
```
apply plugin: 'com.android.library'
apply plugin: 'com.jakewharton.butterknife'
```

app or library build.gradle:
```
dependencies {
  compile 'com.jakewharton:butterknife:8.7.0'
  annotationProcessor 'com.jakewharton:butterknife-compiler:8.7.0'
}
```

## View注入、资源注入、Adapter使用、事件绑定，见链接
[http://jakewharton.github.io/butterknife/](http://jakewharton.github.io/butterknife/)

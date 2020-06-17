# lib-utils
包含很多工具类的依赖包

## 使用指南
### last-version: 
[![last-version](https://jitpack.io/v/cqTeam/lib-utils.svg)](https://jitpack.io/#cqTeam/lib-utils)

### 依赖
1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
    	...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency to module build.gradle:
```
dependencies {
    implementation 'com.github.cqTeam:lib-utils:last-version'
}
```
### 使用
* Init in your Application 

```
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //UI适配初始化
        CustomDensity.init(this,375,667)
        //常用工具初始化
        UtilsManager.init(this,true)
    }
}
```
### END

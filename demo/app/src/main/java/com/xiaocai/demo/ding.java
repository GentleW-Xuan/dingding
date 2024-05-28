package com.xiaocai.demo;

import android.content.Context;
import android.location.Location;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ding implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // 将其它的应用剔除掉
        if (!lpparam.packageName.equals("com.alibaba.android.rimet")){
            return;
        }

        XposedBridge.log("软件的包名是: " + lpparam.packageName);
        XposedHelpers.findAndHookMethod("com.amap.api.location.AMapLocation", //这里是Hook软件的包名+类名
                lpparam.classLoader,
                "getLatitude",  //这里是Hook的软件的方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        //函数返回值
                        //XposedBridge.log("之前的返回值:" + param.getResult());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        //Hook函数之后执行的代码
                        //通过对securityCheck函数的分析发现，只要修改函数的返回值即可实现进入软件
                        //param.setResult(true); //改变返回值
                        //XposedBridge.log("afterHookedMethod isUnlimit");
                        //函数返回值
                        //XposedBridge.log("修改之后的返回值:" + param.getResult());
                        // 修改定位信息
                        param.setResult(25.013183);
                    }
                }
        );

        XposedHelpers.findAndHookMethod("com.amap.api.location.AMapLocation", //这里是Hook软件的包名+类名
                lpparam.classLoader,
                "getLongitude",  //这里是Hook的软件的方法名
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        //函数返回值
                        XposedBridge.log("你好啊你好啊你好啊");
                        //XposedBridge.log("之前的返回值:" + param.getResult());
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        //Hook函数之后执行的代码
                        // 修改定位信息
                        param.setResult(118.780378);
                    }
                }
        );
    }
}

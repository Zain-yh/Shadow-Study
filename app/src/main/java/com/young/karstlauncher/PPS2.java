package com.young.karstlauncher;

import android.util.Log;

import com.tencent.shadow.dynamic.host.PluginProcessService;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class PPS2 extends PluginProcessService {

    public PPS2() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyh", "onCreate: PPS2 启动了");
    }
}

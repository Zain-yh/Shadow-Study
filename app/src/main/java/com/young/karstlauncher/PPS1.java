package com.young.karstlauncher;

import android.util.Log;

import com.tencent.shadow.dynamic.host.PluginProcessService;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class PPS1 extends PluginProcessService {

    public PPS1() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyh", "onCreate: PPS1 启动了");
    }
}

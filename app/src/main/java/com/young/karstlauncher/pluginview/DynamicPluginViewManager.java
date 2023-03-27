package com.young.karstlauncher.pluginview;

import android.content.Context;
import android.view.View;

import com.young.base.pluginview.PluginViewInterface;

import java.io.File;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class DynamicPluginViewManager implements PluginViewInterface {
    ViewImplLoader viewImplLoader;
    PluginViewInterface pluginViewInterface;

    public DynamicPluginViewManager(Context context, File latestImplApk) {
        viewImplLoader = new ViewImplLoader(context, latestImplApk);
        pluginViewInterface = viewImplLoader.load();
    }

    @Override
    public View getView() {
        return pluginViewInterface.getView();
    }
}

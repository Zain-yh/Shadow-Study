package com.young.karstlauncher.pluginview;

import android.content.Context;

import com.tencent.shadow.core.common.InstalledApk;
import com.tencent.shadow.dynamic.apk.ApkClassLoader;
import com.tencent.shadow.dynamic.apk.ChangeApkContextWrapper;
import com.tencent.shadow.dynamic.apk.ImplLoader;
import com.young.base.pluginview.PluginViewFactory;
import com.young.base.pluginview.PluginViewInterface;

import java.io.File;

/**
 * @Desc: 自定义接口来实现
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class ViewImplLoader extends ImplLoader {

    private static final String FACTORY_CLASS_NAME = "com.young.pluginview.PluginViewFactoryImpl";

    private static final String[] REMOTE_PLUGIN_MANAGER_INTERFACES = new String[]
            {
                    "com.tencent.shadow.core.common",
                    "com.tencent.shadow.dynamic.host",
                    "com.young.base.pluginview"
            };

    final private Context applicationContext;
    final private Context mContext;
    final private InstalledApk installedApk;

    ViewImplLoader(Context context, File apk) {
        mContext = context;
        applicationContext = context.getApplicationContext();
        File root = new File(applicationContext.getFilesDir(), "ViewImplLoader");
        File odexDir = new File(root, Long.toString(apk.lastModified(), Character.MAX_RADIX));
        odexDir.mkdirs();
        installedApk = new InstalledApk(apk.getAbsolutePath(), odexDir.getAbsolutePath(), null);
    }

    PluginViewInterface load() {
        ApkClassLoader apkClassLoader = new ApkClassLoader(
                installedApk,
                getClass().getClassLoader(),
                loadWhiteList(installedApk),
                1
        );

        Context contextForApi = new ChangeApkContextWrapper(
                applicationContext,
                installedApk.apkFilePath,
                apkClassLoader
        );

        try {
            PluginViewFactory factory = apkClassLoader.getInterface(
                    PluginViewFactory.class,
                    FACTORY_CLASS_NAME
            );
            return factory.build(contextForApi);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected String[] getCustomWhiteList() {
        return REMOTE_PLUGIN_MANAGER_INTERFACES;
    }
}

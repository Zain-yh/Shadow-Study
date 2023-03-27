package com.young.pluginview;

import android.content.Context;

import com.young.base.pluginview.PluginViewFactory;
import com.young.base.pluginview.PluginViewInterface;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class PluginViewFactoryImpl implements PluginViewFactory {
    @Override
    public PluginViewInterface build(Context hostAppContext) {
        return new TestReturnView(hostAppContext);
    }
}

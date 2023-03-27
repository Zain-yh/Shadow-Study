package com.young.base.pluginview;

import android.content.Context;

/**
 * @Desc: 模仿 sample-hello模块的标准实现  实现自己的api
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public interface PluginViewFactory {
    PluginViewInterface build(Context hostAppContext);
}

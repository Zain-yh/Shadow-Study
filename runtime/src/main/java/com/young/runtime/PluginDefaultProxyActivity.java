package com.young.runtime;

import com.tencent.shadow.core.runtime.container.PluginContainerActivity;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/13
 */
public class PluginDefaultProxyActivity extends PluginContainerActivity {//无需注册在这个模块的Manifest中，要注册在宿主的Manifest中。

    @Override
    protected String getDelegateProviderKey() {
        return "SAMPLE";
    }
}

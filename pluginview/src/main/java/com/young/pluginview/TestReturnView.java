package com.young.pluginview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.base.pluginview.PluginViewInterface;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/14
 */
public class TestReturnView implements PluginViewInterface {

    private final Context context;

    public TestReturnView(Context context) {
        this.context = context;
    }
    @Override
    public View getView() {
        Log.e("zyhTest", "TestReturnView  getView: ");
        TextView textView = new TextView(context);
        textView.setText("我是插件返回的View");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(50);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }
}

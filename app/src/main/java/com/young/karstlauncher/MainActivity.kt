package com.young.karstlauncher

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tencent.shadow.dynamic.host.EnterCallback
import com.young.base.PluginConstant
import com.young.karstlauncher.pluginview.DynamicPluginViewManager

class MainActivity : AppCompatActivity() {

    private lateinit var rootView: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt1 = findViewById<Button>(R.id.start_plugin1)
        bt1.setOnClickListener {
            PluginHelper.getInstance().singlePool.execute {
                val bundle = Bundle().apply {
                    putString(
                        PluginConstant.KEY_PLUGIN_ZIP_PATH,
                        PluginHelper.getInstance().pluginZipFile.absolutePath
                    )
                    putString(PluginConstant.KEY_PLUGIN_PART_KEY, "plugin1")
                    putString(
                        PluginConstant.KEY_ACTIVITY_CLASSNAME,
                        "com.young.plugin1.MainActivity"
                    )
                }
                HostApplication.getApp()
                    .getPluginManager("plugin1", PluginHelper.getInstance().pluginManagerFile)
                    .enter(this,
                        PluginConstant.FROM_ID_START_ACTIVITY.toLong(),
                        bundle,
                        object : EnterCallback {
                            override fun onShowLoadingView(view: View?) {
                                Log.e("zyh", "onShowLoadingView: ")
                            }

                            override fun onCloseLoadingView() {
                                Log.e("zyh", "onCloseLoadingView: ")
                            }

                            override fun onEnterComplete() {
                                Log.e("zyh", "onEnterComplete: ")
                            }
                        })

            }
        }

        val bt2 = findViewById<Button>(R.id.start_plugin2)
        bt2.setOnClickListener {
            PluginHelper.getInstance().singlePool.execute {
                val bundle = Bundle().apply {
                    putString(
                        PluginConstant.KEY_PLUGIN_ZIP_PATH,
                        PluginHelper.getInstance().pluginZipFile2.absolutePath
                    )
                    putString(PluginConstant.KEY_PLUGIN_PART_KEY, "plugin2")
                    putString(
                        PluginConstant.KEY_ACTIVITY_CLASSNAME,
                        "com.young.plugin2.PluginService"
                    )
                }
                HostApplication.getApp()
                        //目前是单个zip 只需要同一个key
                    .getPluginManager("plugin2", PluginHelper.getInstance().pluginManagerFile)
                    .enter(this,
                        PluginConstant.FROM_ID_BIND_SERVICE.toLong(),
                        bundle,
                        object : EnterCallback {
                            override fun onShowLoadingView(view: View?) {
                                Log.e("zyh", "onShowLoadingView: ")
                            }

                            override fun onCloseLoadingView() {
                                Log.e("zyh", "onCloseLoadingView: ")
                            }

                            override fun onEnterComplete() {
                                Log.e("zyh", "onEnterComplete: ")
                            }
                        })

            }
        }

        rootView = findViewById<LinearLayout>(R.id.rootview)
        val addView = findViewById<Button>(R.id.add_plugin_view)
        addView.setOnClickListener {
            PluginHelper.getInstance().singlePool.execute {
                val dynamicPluginViewManager =
                    DynamicPluginViewManager(this, PluginHelper.getInstance().pluginViewFile)
                rootView.post {
                    rootView.addView(dynamicPluginViewManager.view)
                    addView.isEnabled = false
                }
            }
        }

        val updatePath = findViewById<EditText>(R.id.update_path)
        findViewById<Button>(R.id.confirm_update).setOnClickListener {
            PluginHelper.getInstance().updatePlugin1(this@MainActivity, updatePath.text.toString(), object: PluginHelper.UpdateCallback{
                override fun startUpdate() {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "start update", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun endUpdate() {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "update end", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun updateError(err: String?) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, err!!, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}
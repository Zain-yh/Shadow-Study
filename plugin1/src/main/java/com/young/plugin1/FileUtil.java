package com.young.plugin1;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Desc:
 * @Author: zhuyihang
 * @Date: 2022/12/16
 */
public class FileUtil {

    public static String writeToFile(String fileName, String msg) {
        FileOutputStream fos;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(msg);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }






}

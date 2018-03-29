package com.smartwebee.android.blespp;

/**
 * Created by hustweifeng on 2017-03-08.
 */

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.lang.String;


public class CreateFiles {

    //String filenameTemp = "/data/data/com.haoge.tabhost_2/test.txt";

    //创建文件夹及文件
    public void CreateText(String filename, String root) throws IOException {
        //获取内部存储的地址;
        File file = new File(root);
        //文件夹不存在则创建一个文件夹
        if (!file.exists()) {
            try {
                //按照指定的路径创建文件夹
                file.mkdirs();
            }
            catch (Exception e) {
                Log.e("CreateFiles","create_folder_error");
            }
        }

        File dir = new File(root+"/"+filename);
        //文件不存在则创建一个文件
        if (dir.exists()) {
            try {
                //在指定的文件夹中创建文件
                dir.delete();
                dir.createNewFile();
            }
            catch (Exception e) {
                Log.e("CreateFiles","create_file_error");
            }
        }
        if (!dir.exists()) {
            try {
                //在指定的文件夹中创建文件
                dir.createNewFile();
            }
            catch (Exception e) {
                Log.e("CreateFiles","create_file_error");
            }
        }

    }

    //向已创建的文件中写入数据
    public void writeFiles(byte[] buf ,int buf_len, String filename, String root) {
        FileOutputStream fw = null;
        try {
            fw = new FileOutputStream(root+"/"+filename, true);//
            DataOutputStream out=new DataOutputStream(fw);
            // 创建FileWriter对象，用来写入字符流
            //out.writeBytes(str);
            out.write(buf,0,buf_len);
            out.flush();
            out.close();   // 将缓冲对文件的输出
            fw.close();
        } catch (IOException e) {
            Log.e("CreateFiles","writeFiles_error");
            e.printStackTrace();
            try {
                fw.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
            }
        }
    }


    public void renameFile(String path, String oldname, String newname){
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if (newfile.exists()){
                newfile.delete();
            }
            oldfile.renameTo(newfile);
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }

}



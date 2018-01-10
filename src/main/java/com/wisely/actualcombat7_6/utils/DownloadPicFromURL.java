package com.wisely.actualcombat7_6.utils;

import java.io.ByteArrayOutputStream;  
import java.io.DataInputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.net.MalformedURLException;  
import java.net.URL;  
  
public class DownloadPicFromURL {  
    public static void main(String[] args) throws Exception {  
        String url = "http://112.74.12.92:11180/business/api/storage/image?uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMTcxMTI0Lzc3T2I4aGI4b05RUTBDQytHSXR5VEE9PUAxQERFRkFVTFQ=";  
        String path="d:/test/pic1.jpg";  
        FileUtils.makefile(path);
        downloadPicture(url,path);  
    }  
    //链接url下载图片  
    private static void downloadPicture(String urlList,String path) {  
        URL url = null;  
        try {  
            url = new URL(urlList);  
            DataInputStream dataInputStream = new DataInputStream(url.openStream());  
  
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));  
            ByteArrayOutputStream output = new ByteArrayOutputStream();  
  
            byte[] buffer = new byte[1024];  
            int length;  
  
            while ((length = dataInputStream.read(buffer)) > 0) {  
                output.write(buffer, 0, length);  
            }  
            fileOutputStream.write(output.toByteArray());  
            dataInputStream.close();  
            fileOutputStream.close();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
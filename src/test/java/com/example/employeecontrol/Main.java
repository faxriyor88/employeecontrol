package com.example.employeecontrol;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        File file=new File("imagelocation/2bb5cd8c-b182-4f8f-ac4d-153c4c666be0.png");
        String[] strings=file.getName().split("\\.");
        System.out.println(strings[1]);
        String s="qw qq ww";
//        for (int i = 0; i <s.length() ; i++) {
//            if (String.valueOf(s.charAt(i)).equals())
//        }
        String aa=encodeFileToBase64Binary(file);
        System.out.println(aa);
    }
    private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }
}

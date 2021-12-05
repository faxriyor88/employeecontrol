package com.example.employeecontrol;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file=new File("informationaboutemployee/aaa7b834afc-3bd4-4d48-a327-db0048adfa12.docx");
        File file1=new File(file.getPath());
        file1.delete();
        System.out.println(file.getPath());
    }
}

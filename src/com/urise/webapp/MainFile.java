package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Администратор\\Desktop\\TopJava\\basejava");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String str : list) {
                System.out.println(str);
            }
        }


        searchFile(dir);
        showDirectory(1, dir);
    }

    public static void searchFile(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("  Directory:" + file.getName());
                        searchFile(file);
                    } else if (file.isFile()) {
                        System.out.println("File:" + file.getName());
                    }
                }
            }
        }
    }

    public static void showDirectory(int indent, File file) {
        for (int i = 0; i < indent; i++) {
            System.out.print('-');
        }
            System.out.println(file.getName());

        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i< files.length; i++) {
                showDirectory(indent + 4, files[i]);
            }
        }

    }
}

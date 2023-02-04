package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
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

        try (FileInputStream fis = new FileInputStream(file)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        searchFile(file);
    }

    public static void searchFile(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFile(file);
                    }
                }
            }
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file);
                    }
                }
            }
        }
    }
}

package cn.printf.practise.basic.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


public class FilePractiseTest {

    @Test
    public void useFileConstants() {
        System.out.println(File.separator);
        System.out.println(File.pathSeparator);
    }

    @Test
    public void createTempFile() throws IOException {
        File file = File.createTempFile("test", "txt");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void createFileByPath() throws IOException {
        String path = "./text.txt";
        File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println(file.getName());
    }

    @Test
    public void readFile() {
        File file = new File("./test.txt");

        // 基本信息
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isFile());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());

        System.out.println(file.toPath());
        System.out.println(file.toString());
        System.out.println(file.toURI());


        /**
         *  读文件 流程
         *  1. 建立联系，创建 File 对象
         *  2. 选择流，使用文件流
         *  3. 字节数组转字符串
         *  3. 释放资源
         */
        try (
                FileInputStream fileInputStream = new FileInputStream(file)
        ) {
            byte[] bytes = new byte[10];
            int lengh = 0;
            while (-1 != (lengh = fileInputStream.read(bytes))) {
                String info = new String(bytes, 0, lengh);
                System.out.println(info);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeContentToFile() {

        /**
         * 建立联系
         * 选择流 outputsteam
         * 操作
         * flush 强制输出
         * 关闭资源
         */
        File file = new File("./test.txt");
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write("hello world!".getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                System.out.println("关闭输出流失败！");
            }
        }
    }


    /**
     * 使用字符流读取文本文件
     * 建立联系
     * 使用字符流
     * 读入
     * 关闭
     */
    @Test
    public void readFileByCharInputStream() {
        File file = new File("test.txt");
        try (FileReader fileReader = new FileReader(file)) {
            char[] flush = new char[10];
            int len = 0;
            while (-1 != (len = fileReader.read(flush))) {
                System.out.println(flush);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeFileToFile() {
        File file = new File("test.txt");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append("Hello world by file writer!");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFilByBufferedSteam() {
        File file = new File("test.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stringEncodeUTF8() {
        try {
            // 编码
            byte[] bytes = "中国".getBytes("UTF-8");
            // 解码
            System.out.println(new String(bytes, "GBK"));

            // UTF-8
            System.out.println(new String(bytes, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldPrintFileTree() {
        String pathName = ".";

        tree(pathName, 0);
    }

    private void tree(String pathName, int intent) {
        File file = new File(pathName);
        File[] files = file.listFiles();

        for (File one : files) {
            if (one.isDirectory()) {
                tree(one.getPath(), intent++);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < intent; i++) {
                sb.append(".");
            }
            sb.append(one.getName());
            System.out.println(sb.toString());
        }
    }
}

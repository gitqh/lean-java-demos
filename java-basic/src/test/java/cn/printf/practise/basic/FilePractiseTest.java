package cn.printf.practise.basic;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


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
        FileOutputStream fileOutputStream =  null;

        try {
            fileOutputStream =  new FileOutputStream(file);

            fileOutputStream.write("hello world!".getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != fileOutputStream){
                    fileOutputStream.close();
                }
            }catch (Exception e){
                System.out.println("关闭输出流失败！");
            }
        }
    }
}

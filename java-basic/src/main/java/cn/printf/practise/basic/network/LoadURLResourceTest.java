package cn.printf.practise.basic.network;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadURLResourceTest {

    @Test
    public void shouldParseUrl() throws IOException {
        URL url = new URL("Http://baidu.com/#id=1");
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getProtocol());
        System.out.println(url.getContent());
        // 获取锚点
        System.out.println(url.getRef());
    }

    @Test
    public void shouldLoadUrlResource() throws IOException {
        URL url = new URL("Http://baidu.com/");
        InputStream inputStream = url.openStream();

        byte[] flush = new byte[2014];
        int length = 0;
        StringBuilder sb = new StringBuilder();
        while (-1 != (length = inputStream.read(flush))) {
            sb.append(new String(flush, 0, length));
        }

        System.out.println(sb.toString());
        inputStream.close();
    }

    @Test
    public void shouldOutputByBufferStream() throws IOException {
        URL url = new URL("Http://baidu.com/");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

        String flush = null;
        StringBuilder sb = new StringBuilder();
        while ((flush = bufferedReader.readLine()) != null) {
            sb.append(flush);
        }
        bufferedReader.close();
        System.out.println(sb.toString());
    }

    @Test
    public void shouldOutputToFile() throws IOException {
        {
            URL url = new URL("Http://baidu.com/");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./baidu.html")));

            String flush = null;
            while ((flush = bufferedReader.readLine()) != null) {
                bufferedWriter.append(flush);
            }

            bufferedWriter.close();
            bufferedReader.close();
        }
    }
}



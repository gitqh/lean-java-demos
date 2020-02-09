package cn.printf.practise.basic.network.webserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import static cn.printf.practise.basic.network.webserver.CloseUtil.closeIO;
import static cn.printf.practise.basic.network.webserver.Constants.BLANK;
import static cn.printf.practise.basic.network.webserver.Constants.CRLF;

public class Response {

    private BufferedWriter bufferedWriter;

    // 头信息
    private StringBuilder headerInfo;
    // 存储正文长度
    private StringBuilder content;
    private int contentLength = 0;

    public Response() {
        headerInfo = new StringBuilder();
        content = new StringBuilder();
    }

    public Response(OutputStream outputStream) {
        this();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public Response status(int code) {


        // 1. 响应版本信息
        headerInfo.append("HTTP/1.1").append(BLANK);
        headerInfo.append(code).append(BLANK);

        switch (code) {
            case 200:
                headerInfo.append("OK").append(CRLF);
                break;
            case 404:
                headerInfo.append("NOT FOUND").append(CRLF);
                break;
            case 505:
                headerInfo.append("SERVER ERROR").append(CRLF);
                break;
        }

        // 2. 响应头
        headerInfo.append("server: origin Server/0.0.1").append(CRLF);
        headerInfo.append("date:").append(new Date()).append(CRLF);
        headerInfo.append("content-type:text/html;charset=utf-8").append(CRLF);
        headerInfo.append("content-length:").append(contentLength).append(CRLF);
        return this;
    }

    public Response appendBody(String fragment) {
        contentLength += fragment.getBytes().length;
        content.append(fragment);
        return this;
    }

    public Response output() throws IOException {
        bufferedWriter.append(headerInfo.toString());
        bufferedWriter.append(CRLF);
        bufferedWriter.append(content.toString());
        bufferedWriter.flush();
        return this;
    }

    public void close() {
        closeIO(bufferedWriter);
    }
}

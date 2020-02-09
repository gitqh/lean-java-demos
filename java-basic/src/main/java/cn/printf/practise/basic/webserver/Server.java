package cn.printf.practise.basic.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;
import java.util.Date;

public class Server {
    public static final int PORT = 8080;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";
    public static ServerSocket serverSocket;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 启动方法
     */
    private void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务已启动成功，端口号:" + PORT);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收客户端
     */
    public void receive() {
        try {
            Socket client = serverSocket.accept();

            String message = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while ((message = bufferedReader.readLine()).length() > 0) {
                sb.append(message);
                sb.append(CRLF);
                if (null == message) {
                    break;
                }
            }

            String requestInfo = sb.toString().trim();
            System.out.println(requestInfo);

            // 响应返回
            StringBuilder response = new StringBuilder();
            // 准备 body 数据
            String body = "<html><head><title>响应返回</title></head>\n" +
                    "<body>Hello my server!</body></html>\n";

            // 1. 响应版本信息
            response.append("HTTP/1.1").append(BLANK);
            response.append("200").append(BLANK);
            response.append("OK").append(CRLF);
            // 2. 响应头
            response.append("server: origin Server/0.0.1").append(CRLF);
            response.append("date:").append(new Date()).append(CRLF);
            response.append("content-type:text/html;charset=utf-8").append(CRLF);
            response.append("content-length:").append(body.getBytes().length).append(CRLF);
            // 3. 响应消息体
            response.append(CRLF);
            response.append(body);

            System.out.println(response.toString());

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedWriter.write(response.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止方法
     */
    public void stop() {

    }
}

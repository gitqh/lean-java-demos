package cn.printf.practise.basic.network.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static cn.printf.practise.basic.network.webserver.Constants.CRLF;

public class Server {
    public static final int PORT = 8080;

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

            // 准备 body 数据
            String body = "<html><head><title>响应返回</title></head>\n" +
                    "<body>Hello my server!</body></html>\n";

            // 3. 响应消息体
            Response response = new Response(client.getOutputStream());
            response
                    .appendBody(body)
                    .status(200)
                    .output()
                    .close();
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

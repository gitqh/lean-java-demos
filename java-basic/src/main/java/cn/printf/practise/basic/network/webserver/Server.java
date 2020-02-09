package cn.printf.practise.basic.network.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;

    private static ServerSocket serverSocket;
    private static boolean isRunning = true;

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
            while (isRunning) {
                Socket client = serverSocket.accept();

                Request request = new Request(client.getInputStream());
                System.out.println(request.getRequestParams());

                // 测试消息
                String body = "<html><head><title>响应返回</title></head>\n" +
                        "<body>Hello my server!</body></html>\n";

                // 3. 响应消息体
                Response response = new Response(client.getOutputStream());
                response
                        .appendBody(body)
                        .status(200)
                        .output()
                        .close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止方法
     */
    public void stop() {
        System.exit(-1);
    }
}

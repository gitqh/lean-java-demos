package cn.printf.practise.basic.network.tcp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 允许接受多个连接
 */
public class MultiTcpServer {

    public static void main(String[] args) throws IOException {
        // 1. 创建服务器
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            // 2. 接受客户端连接数据，阻塞
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功!");

            // 3. 发送数据
            String message = "这是一个 TCP 连接";

            // 缓冲流
            BufferedWriter bufferedWriter = new BufferedWriter(
                    // 转换流
                    new OutputStreamWriter(socket.getOutputStream())
            );

            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }
}

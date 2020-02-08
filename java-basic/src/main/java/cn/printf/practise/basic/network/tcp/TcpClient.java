package cn.printf.practise.basic.network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {

    /**
     * 1. 创建客户端必须制定服务器地址、端口
     * 2. 客户端的端口由操作系统自动分配
     * 3. 发送数据
     * 4. 接受数据
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String flush = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((flush = bufferedReader.readLine()) != null) {
            stringBuilder.append(flush);
        }

        System.out.println(stringBuilder.toString());
        bufferedReader.close();
    }
}

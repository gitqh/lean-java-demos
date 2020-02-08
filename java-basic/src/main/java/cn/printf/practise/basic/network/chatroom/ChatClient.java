package cn.printf.practise.basic.network.chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        // 启动发送线程
        new Thread(new Send(client)).start();

        // 输入流
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String message = dataInputStream.readUTF();
        System.out.println("客户端收到：" + message);
    }
}

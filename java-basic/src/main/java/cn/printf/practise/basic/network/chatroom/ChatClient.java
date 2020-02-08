package cn.printf.practise.basic.network.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入你的名称进入聊天室：");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        if (null == name || "".equals(name)) {
            System.out.println("程序已结束");
            return;
        }

        Socket client = new Socket("localhost", 9999);
        // 启动发送线程
        new Thread(new Send(client, name)).start();
        // 启动接收线程
        new Thread(new Receive(client)).start();
    }
}

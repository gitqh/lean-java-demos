package cn.printf.practise.basic.network.chatroom;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        // 输出流
        String data = " 数据";
        dataOutputStream.writeUTF(data);
        dataOutputStream.flush();

        // 输入流
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String message = dataInputStream.readUTF();
        System.out.println("客户端收到：" + message);
    }
}

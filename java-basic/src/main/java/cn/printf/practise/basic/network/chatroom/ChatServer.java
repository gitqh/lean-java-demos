package cn.printf.practise.basic.network.chatroom;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("服务器已经启动，端口号:" + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("客户端已经接入");

            DataInput dataInput = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String data = dataInput.readUTF();
                System.out.println(data);

                // 持续读出输出流
                dataOutputStream.writeUTF("服务器 ->> " + data);
                dataOutputStream.flush();
            }
        }
    }
}

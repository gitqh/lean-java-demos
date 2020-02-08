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
            DataInput dataInput = new DataInputStream(socket.getInputStream());
            String data = dataInput.readUTF();
            System.out.println(data);

            // 输出流
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("服务端返回数据");
            dataOutputStream.flush();
        }
    }
}

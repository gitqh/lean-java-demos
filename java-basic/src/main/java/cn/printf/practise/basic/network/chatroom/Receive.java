package cn.printf.practise.basic.network.chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收数据
 */
public class Receive implements Runnable {
    //  从服务器上过来的数据，输入流
    private DataInputStream dis;
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器连接中断");
            CloseUtl.closeAll(dis);
        }
    }

    /**
     * 接收数据
     *
     * @return
     */
    public String receive() {
        String receivedMessage = "";
        try {
            receivedMessage = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
        }
        System.out.println(receivedMessage);
        return receivedMessage;
    }

    @Override
    public void run() {
        while (isRunning) {
            receive();
        }
    }
}


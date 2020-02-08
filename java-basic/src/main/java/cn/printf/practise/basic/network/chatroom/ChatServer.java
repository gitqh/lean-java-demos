package cn.printf.practise.basic.network.chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    public static final int PORT = 9999;

    private List<Channel> channels = new ArrayList<>(100);

    public static void main(String[] args) {
        try {
            new ChatServer().start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接已经中断");
        }
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("服务器已经启动，端口号:" + PORT);

        while (true) {
            Socket client = serverSocket.accept();

            Channel channel = new Channel(client);
            new Thread(channel).start();
            channels.add(channel);

            System.out.println("客户端已经接入");
        }
    }

    /**
     * 一个客户端一条道路
     * 1. 输入流
     * 2. 输出流
     */
    private class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning;
        private String name;

        public Channel(Socket socket) {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                this.name = dis.readUTF();
                dos.writeUTF("进入成功");

                System.out.println(name + "进入到聊天室");
                isRunning = true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("通道建立失败!");

                CloseUtl.closeAll(dis, dos);
                isRunning = false;
            }
        }

        private void send(String message) {
            if (null == message || message.equals("")) {
                return;
            }
            try {
                dos.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }

        /**
         * send message to other client
         *
         * @param message
         */
        private void sendOthers(String message) {
            if (null == message || message.equals("")) {
                return;
            }

            for (Channel channel : channels) {
                if (channel != this) {
                    channel.send(message);
                }
            }
        }

        private String receive() {
            String message = "";
            try {
                message = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
            return message;
        }

        @Override
        public void run() {
            while (isRunning) {
                sendOthers(this.name + ":" + receive());
            }
        }
    }
}

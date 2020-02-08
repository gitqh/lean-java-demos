package cn.printf.practise.basic.network.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

    public static void main(String[] args) throws IOException {
        // 启动服务器
        DatagramSocket server = new DatagramSocket(8080);
        // 准备接收数据包
        byte[] container = new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(container,container.length);
        // 接收数据
        server.receive(datagramPacket);

        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength();

        String s = new String(data, 0, length);
        System.out.println(s);

        // 释放资源
        server.close();
    }

}

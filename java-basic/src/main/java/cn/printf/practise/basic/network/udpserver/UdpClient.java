package cn.printf.practise.basic.network.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class UdpClient {

    public static void main(String[] args) throws IOException {
        // 准备客户端
        DatagramSocket client = new DatagramSocket();

        String data = "UDP 编程";
        byte[] flush = data.getBytes();
        DatagramPacket clientPacket = new DatagramPacket(flush, flush.length, new InetSocketAddress(
                "localhost",
                8080
        ));

        client.send(clientPacket);
        client.close();
    }
}

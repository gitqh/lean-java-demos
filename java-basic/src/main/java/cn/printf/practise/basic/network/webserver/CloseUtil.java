package cn.printf.practise.basic.network.webserver;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

class CloseUtil {
    static <T extends Closeable> void closeIO(T... io) {
        for (Closeable temp : io) {
            if (null != temp) {
                try {
                    temp.close();
                } catch (IOException e) {
                }
            }
        }
    }

    static void closeSocket(Socket socket) {
        if (null != socket) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}

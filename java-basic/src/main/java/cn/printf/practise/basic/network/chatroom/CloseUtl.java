package cn.printf.practise.basic.network.chatroom;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtl {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            if (null != temp) {
                try {
                    temp.close();
                } catch (IOException e) {
                    // TODO handle exception
                }
            }
        }
    }
}

package cn.printf.practise.basic.network.webserver;

import java.io.Closeable;
import java.io.IOException;

class CloseUtil {
    static void closeIO(Closeable... io) {
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

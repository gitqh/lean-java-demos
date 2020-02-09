package cn.printf.practise.basic.network.webserver;

import java.io.IOException;

public class Servlet {
    public void service(Request request, Response response) throws IOException {
        // 测试消息
        String body = "<html><head><title>响应返回</title></head>\n" +
                "<body>Hello my server!</body></html>\n";
        response
                .appendBody(body)
                .status(200)
                .output()
                .close();
    }
}

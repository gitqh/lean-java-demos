package cn.printf.practise.basic.network.webserver;

public class RegisterServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) throws Exception {
        // 测试消息
        String body = "<html><head><title>响应返回</title></head>\n" +
                "<body>Hello my server!</body></html>\n";
        response
                .appendBody(body)
                .status(200)
                .output();
    }

    @Override
    public void doPost(Request request, Response response) throws Exception {
        response
                .status(405)
                .output();
    }
}

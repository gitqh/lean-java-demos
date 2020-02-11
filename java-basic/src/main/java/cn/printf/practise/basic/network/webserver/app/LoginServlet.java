package cn.printf.practise.basic.network.webserver.app;

import cn.printf.practise.basic.network.webserver.dispatch.Request;
import cn.printf.practise.basic.network.webserver.dispatch.Response;
import cn.printf.practise.basic.network.webserver.dispatch.Servlet;

import java.util.Map;

public class LoginServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) throws Exception {
        // 测试消息
        String body;
        if (login(request.getRequestParams())) {
            body = "<html><head><title>响应返回</title></head>\n" +
                    "<body>登录成功!</body></html>\n";
        } else {
            body = "<html><head><title>响应返回</title></head>\n" +
                    "<body>登录失败!</body></html>\n";
        }

        response
                .appendBody(body)
                .status(200);
    }

    private boolean login(Map<String, String> requestParams) {
        return "admin".equals(requestParams.get("username")) && "123456".equals(requestParams.get("password"));
    }

    @Override
    public void doPost(Request request, Response response) throws Exception {
        response
                .status(405);
    }
}

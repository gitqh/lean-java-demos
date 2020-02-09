package cn.printf.practise.basic.network.webserver;

import java.util.Map;

public class WebApp {
    private static ServletContext context;

    static {
        context = new ServletContext();

        // 模拟数据
        Map<String, String> mapping = context.getMapping();
        mapping.put("/login", "login");
        mapping.put("/log", "login");
        mapping.put("/register", "register");

        Map<String, Servlet> servlets = context.getServlets();
        servlets.put("login", new LoginServlet());
        servlets.put("register", new RegisterServlet());
    }

    public static Servlet getServlet(String path) {
        return context.getServlets().get(context.getMapping().get(path));
    }
}

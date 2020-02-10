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

        Map<String, String> servlets = context.getServlets();
        servlets.put("login", "cn.printf.practise.basic.network.webserver.LoginServlet");
        servlets.put("register", "cn.printf.practise.basic.network.webserver.RegisterServlet");
    }

    public static Servlet getServlet(String path) {
        String servletClassName = context.getServlets().get(context.getMapping().get(path));
        if (null != servletClassName) {
            try {
                Class<?> servletClass = Class.forName(servletClassName);
                return (Servlet) servletClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }
}

package cn.printf.practise.basic.network.webserver;

import cn.printf.practise.basic.network.webserver.exception.InitWebException;
import cn.printf.practise.basic.network.webserver.xml.WebAppDocument;
import cn.printf.practise.basic.network.webserver.xml.WebXmlparser;

import java.util.Map;

public class WebApp {
    private static ServletContext context;
    private static WebXmlparser webXmlparser;

    static {
        context = new ServletContext();

        // 模拟数据，通过 xml 构建

        // TODO parse xml 得到 context
        try {
            WebAppDocument webAppDocument = WebXmlparser.parseWebXml();
            System.out.println(webAppDocument);
        } catch (InitWebException e) {
            e.printStackTrace();
        }

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

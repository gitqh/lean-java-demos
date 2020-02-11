package cn.printf.practise.basic.network.webserver.dispatch;

import cn.printf.practise.basic.network.webserver.exception.InitWebException;
import cn.printf.practise.basic.network.webserver.xml.WebAppDocument;
import cn.printf.practise.basic.network.webserver.xml.WebXmlparser;

public class WebApp {
    private static ServletContext context;

    static {
        try {
            WebAppDocument webAppDocument = WebXmlparser.parseWebXml();
            context = ServletContext.fromWebXmlDocument(webAppDocument);
        } catch (InitWebException e) {
            e.printStackTrace();

            System.out.println("启动失败");
            System.exit(-1);
        }
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

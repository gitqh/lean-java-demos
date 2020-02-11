package cn.printf.practise.basic.network.webserver.dispatch;

import cn.printf.practise.basic.network.webserver.xml.MappingElement;
import cn.printf.practise.basic.network.webserver.xml.ServletElement;
import cn.printf.practise.basic.network.webserver.xml.WebAppDocument;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
    // servlet 容器
    private Map<String, String> servlets;
    // url request mapping
    private Map<String, String> mapping;

    private ServletContext() {
        this.servlets = new HashMap<>();
        this.mapping = new HashMap<>();
    }

    public static ServletContext fromWebXmlDocument(WebAppDocument webAppDocument) {
        ServletContext servletContext = new ServletContext();

        // url 作为 key 进行地址匹配
        for (MappingElement mappingElement : webAppDocument.getMappingElements()) {
            servletContext.mapping.put(mappingElement.getUrlPattern(), mappingElement.getName());
        }
        for (ServletElement servletElement : webAppDocument.getServletElements()) {
            servletContext.servlets.put(servletElement.getName(), servletElement.getServletClass());
        }
        return servletContext;
    }

    public Map<String, String> getServlets() {
        return servlets;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }
}

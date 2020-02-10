package cn.printf.practise.basic.network.webserver;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
    // servlet 容器
    private Map<String, String> servlets;
    // url request mapping
    private Map<String, String> mapping;

    public ServletContext() {
        this.servlets = new HashMap<>();
        this.mapping = new HashMap<>();
    }

    public Map<String, String> getServlets() {
        return servlets;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }
}

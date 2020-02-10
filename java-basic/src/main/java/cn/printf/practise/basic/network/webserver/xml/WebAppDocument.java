package cn.printf.practise.basic.network.webserver.xml;

import java.util.ArrayList;
import java.util.List;

public class WebAppDocument {
    private List<MappingElement> mappingElements;
    private List<ServletElement> servletElements;

    public WebAppDocument() {
        this.mappingElements = new ArrayList<>();
        this.servletElements = new ArrayList<>();
    }

    public List<MappingElement> getMappingElements() {
        return mappingElements;
    }

    public void setMappingElements(List<MappingElement> mappingElements) {
        this.mappingElements = mappingElements;
    }

    public List<ServletElement> getServletElements() {
        return servletElements;
    }

    public void setServletElements(List<ServletElement> servletElements) {
        this.servletElements = servletElements;
    }
}

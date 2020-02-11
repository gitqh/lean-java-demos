package cn.printf.practise.basic.network.webserver.xml;

public class ServletElementHandler implements ElementHandler<ServletElement> {
    @Override
    public ServletElement startElement() {
        return new ServletElement();
    }

    @Override
    public void handleChildValue(String qName, String value, ServletElement servletElement) {
        switch (qName) {
            case "name":
                servletElement.setName(value);
                break;
            case "servlet-class":
                servletElement.setServletClass(value);
                break;
        }
    }
}

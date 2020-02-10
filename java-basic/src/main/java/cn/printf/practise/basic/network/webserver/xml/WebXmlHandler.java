package cn.printf.practise.basic.network.webserver.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * 想写一个通用的 web xml handler TODO 参考 com.sun.beans.decoder.ElementHandler 的递归实现
 */
public class WebXmlHandler extends DefaultHandler {
    private WebAppDocument webAppDocument = new WebAppDocument();

    private String currentContextTag = null;
    private ElementHandler currentElementHandler = null;
    private Object currentElement = null;
    private HashMap<String, ElementHandler> elementHandlers = new HashMap<>();

    public WebXmlHandler() {
        super();
        this.elementHandlers.put("mapping", new MappingElementHandler());
        this.elementHandlers.put("servlet", new ServletElementHandler());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentContextTag = qName;
        System.out.println("开始解析" + currentContextTag);

        if ("web-app".equals(qName)) {
            return;
        }
        // 已经开始
        if (null != currentElement) {
            return;
        }
        if (null == elementHandlers.get(qName)) {
            throw new RuntimeException("not supported tag");
        } else {
            // 开始解析
            currentElementHandler = elementHandlers.get(qName);
            currentElement = currentElementHandler.startElement();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.println("解析" + currentContextTag);
        if (null != currentElementHandler && null != currentContextTag) {
            String value = new String(ch, start, length);
            currentElementHandler.handleChildValue(currentContextTag, value, currentElement);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        currentContextTag = null;

        System.out.println("停止" + currentContextTag);

        if (null != currentElementHandler && elementHandlers.get(qName) == currentElementHandler) {

            switch (qName) {
                case "mapping":
                    webAppDocument.getMappingElements().add((MappingElement) currentElement);
                    break;
                case "servlet":
                    webAppDocument.getServletElements().add((ServletElement) currentElement);
                    break;
            }
            //  清理数据
            currentElementHandler = null;
            currentElement = null;
        }
    }

    public WebAppDocument getWebAppDocument() {
        return webAppDocument;
    }
}

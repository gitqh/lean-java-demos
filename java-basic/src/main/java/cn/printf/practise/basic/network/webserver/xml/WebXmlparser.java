package cn.printf.practise.basic.network.webserver.xml;

import cn.printf.practise.basic.network.webserver.util.CloseUtil;
import cn.printf.practise.basic.network.webserver.exception.InitWebException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class WebXmlparser {

    private static final String xmlConfigLocation = "cn/printf/practise/basic/network/webserver/WEB_INFO/web.xml";

    public static WebAppDocument parseWebXml() throws InitWebException {
        InputStream webXmlInputStream = null;
        try {
            // 1. 获取工厂
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            // 2. 从解析工厂获取解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // 3. 加载 xml 流
            webXmlInputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(xmlConfigLocation);
            // 3. 加载处理器
            WebXmlHandler webXmlHandler = new WebXmlHandler();
            saxParser.parse(webXmlInputStream, webXmlHandler);

            return webXmlHandler.getWebAppDocument();
        } catch (Exception e) {
            throw new InitWebException("parse xml failed", e);
        } finally {
            CloseUtil.closeIO(webXmlInputStream);
        }
    }
}

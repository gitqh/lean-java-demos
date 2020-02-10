package cn.printf.practise.basic.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ParsePerson {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1. 获取工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();

        // 3. 加载处理器
        PersonHandler dh = new PersonHandler();
        saxParser.parse(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("cn/printf/practise/basic/xml/person.xml"),
                dh
        );
        System.out.println(dh.getPersons());
    }
}

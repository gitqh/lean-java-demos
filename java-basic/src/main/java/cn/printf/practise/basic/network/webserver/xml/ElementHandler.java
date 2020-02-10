package cn.printf.practise.basic.network.webserver.xml;

public interface ElementHandler<T> {
    T startElement();
    void handleChildValue(String qName, String value, T t);
}

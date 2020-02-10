package cn.printf.practise.basic.network.webserver.exception;

public class InitWebException extends Exception {
    public InitWebException(String parseXmlFailed, Exception e) {
        super(parseXmlFailed, e);
    }
}

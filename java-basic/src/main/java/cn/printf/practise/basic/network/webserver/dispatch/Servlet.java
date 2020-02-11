package cn.printf.practise.basic.network.webserver.dispatch;

public abstract class Servlet {
    public void service(Request request, Response response) throws Exception {
        doGet(request, response);
        doPost(request, response);
    }

    public abstract void doGet(Request request, Response response) throws Exception;

    public abstract void doPost(Request request, Response response) throws Exception;
}

package cn.printf.practise.basic.network.webserver;


import java.io.IOException;
import java.net.Socket;

/**
 * 每个请求一个实例
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request request;
    private Response response;

    public Dispatcher(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            request = new Request(client.getInputStream());
            response = new Response(client.getOutputStream());
            Servlet servlet = new Servlet();
            servlet.service(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeSocket(client);
            CloseUtil.closeIO(request, response);
        }
    }
}

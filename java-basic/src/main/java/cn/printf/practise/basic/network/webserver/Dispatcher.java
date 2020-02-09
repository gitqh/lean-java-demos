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
            Servlet servlet = WebApp.getServlet(request.getPath());
            if (null == servlet) {
                response.appendBody("not found").status(404).output();
                return;
            }
            servlet.service(request, response);
            response.output();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeSocket(client);
            CloseUtil.closeIO(request, response);
        }
    }
}

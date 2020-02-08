package cn.printf.practise.basic.network.chatroom;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送线程
 */
public class Send implements Runnable {

    // 控制台读取数据的流
    private BufferedReader console;
    // 发送网络数据流
    private DataOutputStream dos;
    // 结束线程的标记
    private boolean isRunning = true;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtl.closeAll(dos, console);
        }
    }

    /**
     * 1. 从控制台接受数据
     * 2. 发送数据
     */
    public void send() {
        String message = getMessageFromConsole();
        if (null != message && !message.equals("")) {
            try {
                dos.writeUTF(message);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("发生异常运行结束");
                isRunning = false;
                CloseUtl.closeAll(dos, console);
            }
        }
    }

    private String getMessageFromConsole() {
        String s = "";
        try {
            s = console.readLine();
        } catch (IOException e) {
             e.printStackTrace();
        }
        return s;
    }

    @Override
    public void run() {
        while (isRunning) {
            send();
        }
    }
}

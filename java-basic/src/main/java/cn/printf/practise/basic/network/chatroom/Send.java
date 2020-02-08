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
    // 当前用户名称
    private String name = "";

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client, String name) {
        this();
        this.name = name;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            send(this.name);
        } catch (IOException e) {
            isRunning = false;
            CloseUtl.closeAll(dos, console);
        }
    }

    /**
     * 1. 从控制台接受数据
     * 2. 发送数据
     */
    public void send(String message) {
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
            String messageFromConsole = getMessageFromConsole();
            System.out.println("我:" + messageFromConsole);

            send(messageFromConsole);
        }
    }
}

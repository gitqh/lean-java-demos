package cn.printf.practise.basic.network.webserver;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static cn.printf.practise.basic.network.webserver.Constants.BLANK;

public class Request implements Closeable {
    private String method;
    private String path;
    private String protocol;
    private Map<String, String> requestParams;
    private String content;
    private Map<String, String> headers;
    private String requestInfo;
    private BufferedReader bufferedReader;

    public Request() {
        method = "";
        path = "";
        requestParams = new HashMap<>();
        headers = new HashMap<>();
        content = "";
    }

    public Request(InputStream inputStream) throws IOException {
        this();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        parseRequest();
    }

    /**
     * 1. 解析第一行 header
     * 2. 解析 到空行
     * 3. 解析到 body
     *
     * @throws IOException
     */
    private void parseRequest() throws IOException {
        String line = null;
        StringBuilder sb = new StringBuilder();
        parseFirstLine();
        while ((line = bufferedReader.readLine()).length() > 0) {
            String[] split = line.split(":");
            // 解析头信息
            headers.put(split[0], split[1]);
            // TODO 读取 content
            if (null == line) {
                break;
            }
        }
    }

    /**
     * 解析基本信息
     *
     * @throws IOException
     */
    private void parseFirstLine() throws IOException {
        String firstLine = bufferedReader.readLine();
        String[] metas = firstLine.split(BLANK);
        String originPath = URLDecoder.decode(metas[1], "utf-8");

        method = metas[0];
        protocol = metas[2];
        path = basePath(originPath);

        parseParameterMapValue(originPath);
    }

    private String basePath(String originPath) {
        int index = originPath.indexOf("?");
        if (index != -1) {
            return originPath.substring(0, index);
        } else {
            return originPath;
        }
    }

    private void parseParameterMapValue(String path) throws MalformedURLException {
        if (path.contains("?")) {
            String queryString = path.substring(path.indexOf("?") + 1);
            String[] paramStrings = queryString.split("&");
            for (String paramString : paramStrings) {
                String[] paramPair = paramString.split("=");
                if (paramPair.length > 1) {
                    requestParams.put(paramPair[0].trim(), paramPair[1].trim());
                } else {
                    requestParams.put(paramPair[0].trim(), null);
                }
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    @Override
    public void close() throws IOException {
        CloseUtil.closeIO(bufferedReader);
    }
}

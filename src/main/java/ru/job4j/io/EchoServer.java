package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. Бот [#7921]
 * 4. Slf4j - вывод exception. [#268853]
 */
public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("msg=Hello ")) {
                            out.write("Hello".getBytes());
                        } else if (str.contains("msg=Exit ")) {
                            socket.close();
                            server.close();
                        } else if (str.contains("msg=")) {
                            out.write("What".getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception", e);
        }
    }
}

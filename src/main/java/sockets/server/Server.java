package sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6666);
        System.out.println("server is running...");

        for (;;) {
            Socket sock = ss.accept();
            System.out.println("connect from " + sock.getRemoteSocketAddress());
            Thread t = new ServerHandler(sock);
            t.start();
        }
    }
}

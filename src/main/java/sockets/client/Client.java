package sockets.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost", 6666);
        try (InputStream input = sock.getInputStream()) {
            try (OutputStream output = sock.getOutputStream()) {
                handle(input, output);
            }
        }
        sock.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yyyy/MM/dd HH:mm:ss]");
        System.out.println(dtf.format(LocalDateTime.now()) + " [Server] " + reader.readLine());
        for (;;) {
            // 正常对话工作模式
            System.out.print(dtf.format(LocalDateTime.now()) + " >>> ");
            String s = scanner.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println(dtf.format(LocalDateTime.now()) + " <<< " + resp);

            // test 通讯时间
            if (resp.equals("ok: test")) {
                int[] data = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
                String dataString = Arrays.toString(data);
                System.out.println("test data: " + dataString);
                System.out.println("data size: " + dataString.getBytes(StandardCharsets.UTF_8).length);

                long startTime = System.nanoTime();
                long maxTime = Long.MIN_VALUE;
                long minTime = Long.MAX_VALUE;
                for (int i = 0; i < 1000; i++) {
                    // System.out.println("begin round: " + i);
                    long startRoundTime = System.nanoTime();
                    writer.write(dataString);
                    writer.newLine();
                    writer.flush();
                    String res = reader.readLine();
                    if (res.equals("ok: " + dataString)) {
                        long endRoundTime = System.nanoTime();
                        long roundTime = endRoundTime - startRoundTime;
                        maxTime = Math.max(maxTime, roundTime);
                        minTime = Math.min(minTime, roundTime);
                        // System.out.println("Round " + (i + 1) + " time: " + (endRoundTime - startRoundTime) + "ns");
                    } else {
                        System.out.println("Round " + (i + 1) + ", client can't receive data!");
                    }
                }
                long endTime = System.nanoTime();
                System.out.println("Total time (1000 rounds): " + (endTime - startTime) + "ns");
                System.out.println("Round i maxtime (1000 rounds): " + maxTime + "ns");
                System.out.println("Round i mintime (1000 rounds): " + minTime + "ns");
            }

            // client 退出
            if (resp.equals("bye")) {
                break;
            }
        }
    }
}

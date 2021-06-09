package grpc;

import grpc.abc.MyRequest;
import grpc.abc.MyResponse;
import grpc.abc.TimeCountGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TimeTestClient {
    private final TimeCountGrpc.TimeCountBlockingStub blockingStub;

    // Construct client for accessing HelloWorld server using the existing channel.
    public TimeTestClient(Channel channel) {
        blockingStub = TimeCountGrpc.newBlockingStub(channel);
    }

    /** Test communicate time with server. */
    public void communicate(String message) {
        System.out.println("Will try to test...");

        // 初始化用于测试的 message
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
        message = Arrays.toString(data);
        System.out.println("test message: " + message);
        System.out.println("message size: " + message.getBytes(StandardCharsets.UTF_8).length);

        System.out.println("---------- Test begin ----------");
        long startTime = System.nanoTime();
        long maxTime = Long.MIN_VALUE;
        long minTime = Long.MAX_VALUE;
        for (int i = 0; i < 1000; i++) {
            // System.out.println("begin round: " + i);
            long startRoundTime = System.nanoTime();

            // 收发 message
            MyRequest request = MyRequest.newBuilder().setDatasend(message).build();
            MyResponse response;
            try {
                response = blockingStub.getMessageByMyMessage(request);
                String res = response.getDataget();
                long endRoundTime = System.nanoTime();
                long roundTime = endRoundTime - startRoundTime;
                maxTime = Math.max(maxTime, roundTime);
                minTime = Math.min(minTime, roundTime);
                // System.out.println("Round " + (i + 1) + " time: " + (endRoundTime - startRoundTime) + "ns");
            } catch (StatusRuntimeException e) {
                System.out.println(Level.WARNING + "RPC failed: {0}" + e.getStatus());
                return;
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Total time (1000 rounds): " + (endTime - startTime) + "ns");
        System.out.println("Round i maxtime (1000 rounds): " + maxTime + "ns");
        System.out.println("Round i mintime (1000 rounds): " + minTime + "ns");

        System.out.println("---------- Test over ----------");
    }

    public static void main(String[] args) throws Exception{
        String message = "world";
        String target = "localhost:6666";

        // 命令行参数解析
        if (args.length > 0) {
            if ("--help".equals(args[0])) {
                System.err.println("Usage: [name [target]]");
                System.err.println("");
                System.err.println("  name    The name you wish to be greeted by. Defaults to " + message);
                System.err.println("  target  The server to connect to. Defaults to " + target);
                System.exit(1);
            }
            message = args[0];
        }
        if (args.length > 1) {
            target = args[1];
        }

        // Create a communication channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        try {
            TimeTestClient client = new TimeTestClient(channel);
            client.communicate(message);
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}

package grpc;

import grpc.abc.MyRequest;
import grpc.abc.MyResponse;
import grpc.abc.TimeCountGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Server that manages startup/shutdown of a server.
 */
public class TimeTestServer {
    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 6666;
        server = ServerBuilder.forPort(port)
                .addService(new TimeCountImpl())
                .build()
                .start();
        System.out.println("*** gRPC java server started !");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down !");
                try {
                    TimeTestServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down !");
            }
        });
    }

    public void stop() throws InterruptedException{
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final TimeTestServer server = new TimeTestServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class TimeCountImpl extends TimeCountGrpc.TimeCountImplBase {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yyyy/MM/dd HH:mm:ss]");
        @Override
        public void getMessageByMyMessage(MyRequest request, StreamObserver<MyResponse> responseObserver) {
            System.out.println(dtf.format(LocalDateTime.now()) + " [Server] The message from client :" + request.getDatasend());
            responseObserver.onNext(MyResponse.newBuilder().setDataget(request.getDatasend()).build());
            responseObserver.onCompleted();
        }

    }
}

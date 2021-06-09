package grpc.abc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.0)",
    comments = "Source: TimeTest.proto")
public final class TimeCountGrpc {

  private TimeCountGrpc() {}

  public static final String SERVICE_NAME = "grpc.TimeCount";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetMessageByMyMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMessageByMyMessage",
      requestType = MyRequest.class,
      responseType = MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetMessageByMyMessageMethod() {
    io.grpc.MethodDescriptor<MyRequest, MyResponse> getGetMessageByMyMessageMethod;
    if ((getGetMessageByMyMessageMethod = TimeCountGrpc.getGetMessageByMyMessageMethod) == null) {
      synchronized (TimeCountGrpc.class) {
        if ((getGetMessageByMyMessageMethod = TimeCountGrpc.getGetMessageByMyMessageMethod) == null) {
          TimeCountGrpc.getGetMessageByMyMessageMethod = getGetMessageByMyMessageMethod =
              io.grpc.MethodDescriptor.<MyRequest, MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMessageByMyMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TimeCountMethodDescriptorSupplier("GetMessageByMyMessage"))
              .build();
        }
      }
    }
    return getGetMessageByMyMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TimeCountStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimeCountStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimeCountStub>() {
        @Override
        public TimeCountStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimeCountStub(channel, callOptions);
        }
      };
    return TimeCountStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TimeCountBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimeCountBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimeCountBlockingStub>() {
        @Override
        public TimeCountBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimeCountBlockingStub(channel, callOptions);
        }
      };
    return TimeCountBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TimeCountFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimeCountFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimeCountFutureStub>() {
        @Override
        public TimeCountFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimeCountFutureStub(channel, callOptions);
        }
      };
    return TimeCountFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TimeCountImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMessageByMyMessage(MyRequest request,
                                      io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMessageByMyMessageMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMessageByMyMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MyRequest,
                MyResponse>(
                  this, METHODID_GET_MESSAGE_BY_MY_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class TimeCountStub extends io.grpc.stub.AbstractAsyncStub<TimeCountStub> {
    private TimeCountStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TimeCountStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimeCountStub(channel, callOptions);
    }

    /**
     */
    public void getMessageByMyMessage(MyRequest request,
                                      io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMessageByMyMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TimeCountBlockingStub extends io.grpc.stub.AbstractBlockingStub<TimeCountBlockingStub> {
    private TimeCountBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TimeCountBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimeCountBlockingStub(channel, callOptions);
    }

    /**
     */
    public MyResponse getMessageByMyMessage(MyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMessageByMyMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TimeCountFutureStub extends io.grpc.stub.AbstractFutureStub<TimeCountFutureStub> {
    private TimeCountFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TimeCountFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimeCountFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MyResponse> getMessageByMyMessage(
        MyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMessageByMyMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MESSAGE_BY_MY_MESSAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TimeCountImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TimeCountImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MESSAGE_BY_MY_MESSAGE:
          serviceImpl.getMessageByMyMessage((MyRequest) request,
              (io.grpc.stub.StreamObserver<MyResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TimeCountBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TimeCountBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TimeTestProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TimeCount");
    }
  }

  private static final class TimeCountFileDescriptorSupplier
      extends TimeCountBaseDescriptorSupplier {
    TimeCountFileDescriptorSupplier() {}
  }

  private static final class TimeCountMethodDescriptorSupplier
      extends TimeCountBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TimeCountMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TimeCountGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TimeCountFileDescriptorSupplier())
              .addMethod(getGetMessageByMyMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}

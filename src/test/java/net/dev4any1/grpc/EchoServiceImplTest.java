package net.dev4any1.grpc;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.grpc.stub.StreamObserver;
import net.dev4any1.grpc.impl.EchoServiceImpl;

class EchoServiceImplTest {

    private EchoServiceImpl echoService;
    private StreamObserver<EchoResponse> responseObserver;

    @SuppressWarnings("unchecked")
	@BeforeEach
    void setUp() {
        echoService = new EchoServiceImpl();
        responseObserver = mock(StreamObserver.class);
    }

    @Test
    void testEcho() {
        EchoRequest request = EchoRequest.newBuilder()
                .setMessage("Hello, gRPC!")
                .build();

        echoService.echo(request, responseObserver);

        EchoResponse expectedResponse = EchoResponse.newBuilder()
                .setMessage("Echo: Hello, gRPC!")
                .build();
        verify(responseObserver).onNext(expectedResponse);
        verify(responseObserver).onCompleted();
    }
}
package net.dev4any1.grpc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import net.dev4any1.grpc.impl.EchoServiceImpl;

class EchoServiceIntegrationTest {

	private Server server;
	private ManagedChannel channel;
	private EchoServiceGrpc.EchoServiceBlockingStub blockingStub;

	@BeforeEach
	void setUp() throws IOException {
		String serverName = InProcessServerBuilder.generateName();
		// starting a server add service, using direct executor for testing
		server = InProcessServerBuilder.forName(serverName).directExecutor().addService(new EchoServiceImpl()).build()
				.start();

		// building a client channel with same direct testing executor
		channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
		blockingStub = EchoServiceGrpc.newBlockingStub(channel);
	}

	@AfterEach
	void tearDown() {
		server.shutdown();
		channel.shutdown();
	}

	@Test
	void testEcho() {
		long timeStart = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			EchoRequest request = EchoRequest.newBuilder().setMessage("Hello, gRPC!").build();
			EchoResponse response = blockingStub.echo(request);
			assertEquals("Echo: Hello, gRPC!", response.getMessage());
		}
		System.out.println("10K done at " + (System.currentTimeMillis() - timeStart));
	}
}
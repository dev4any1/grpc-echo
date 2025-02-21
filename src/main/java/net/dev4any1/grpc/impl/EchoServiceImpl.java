package net.dev4any1.grpc.impl;

import io.grpc.stub.StreamObserver;
import net.dev4any1.grpc.EchoRequest;
import net.dev4any1.grpc.EchoResponse;
import net.dev4any1.grpc.EchoServiceGrpc;

public class EchoServiceImpl extends EchoServiceGrpc.EchoServiceImplBase {

	@Override
	public void echo(EchoRequest req, StreamObserver<EchoResponse> responseObserver) {
		EchoResponse response = EchoResponse.newBuilder().setMessage("Echo: " + req.getMessage()).build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}

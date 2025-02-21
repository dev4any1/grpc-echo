package net.dev4any1.grpc.impl;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(9090).addService(new EchoServiceImpl()).build();
		server.start();
		System.out.println("gRPC Server started on port 9090");
		server.awaitTermination();
	}
}
package com.stgconsulting.microdocker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface HelloProducerChannels {
	String HELLO = "hello";

	String RESPONSE = "response";

	@Output(HelloProducerChannels.HELLO)
	SubscribableChannel hello();

	@Input(HelloProducerChannels.RESPONSE)
	SubscribableChannel response();
}

package com.stgconsulting.microdocker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface HelloResponderChannels {
	String HELLO_COUNTED = "hello_counted";

	String RESPONSE = "response";

	@Input(HelloResponderChannels.HELLO_COUNTED)
	SubscribableChannel hello();

	@Output(HelloResponderChannels.RESPONSE)
	SubscribableChannel response();
}

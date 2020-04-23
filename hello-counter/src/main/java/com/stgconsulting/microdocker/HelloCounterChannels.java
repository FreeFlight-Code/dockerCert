package com.stgconsulting.microdocker;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface HelloCounterChannels {
	String HELLO = "hello";

	String HELLO_COUNTED = "hello_counted";

	@Output(HelloCounterChannels.HELLO_COUNTED)
	SubscribableChannel counted();

	@Input(HelloCounterChannels.HELLO)
	SubscribableChannel hello();
}

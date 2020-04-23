package com.stgconsulting.microdocker;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.stgconsulting.microdocker.domain.HelloCountedMessage;

@Component
public class HelloResponderController {

	private final static String MESSAGE = "Hello %s from %s! count: %s";

	private HelloResponderChannels channels;

	private long id;

	@Autowired
	public HelloResponderController(HelloResponderChannels channels) {
		this.channels = channels;
		this.id = new Random().nextLong(); // pseudo-unique id of node
	}

	// take in from broker, create response, publish to broker
	@StreamListener(HelloResponderChannels.HELLO_COUNTED)
	public void sayHello(HelloCountedMessage message) {
		String response = String.format(MESSAGE, message.getName(), id, message.getCount());
		channels.response().send(MessageBuilder.withPayload(response).build());
		System.out.println(response);
	}
}

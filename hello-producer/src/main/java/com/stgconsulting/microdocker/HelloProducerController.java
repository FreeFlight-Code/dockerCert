package com.stgconsulting.microdocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import com.stgconsulting.microdocker.domain.HelloMessage;
import com.stgconsulting.microdocker.domain.HelloResponse;

@Controller
@EnableBinding(HelloProducerChannels.class)
public class HelloProducerController {

	private HelloProducerChannels channels;

	private SimpMessagingTemplate webSocket;

	@Autowired
	public HelloProducerController(HelloProducerChannels channels, SimpMessagingTemplate websocket) {
		this.channels = channels;
		this.webSocket = websocket;
	}

	// take in a message via websocket, publish to broker
	@MessageMapping("/hello")
	public String publishMessage(HelloMessage message) {

		System.out.println("Received name: " + message.getName());

		// send message to channel
		channels.hello().send(MessageBuilder.withPayload(message).build());
		return "OK";
	}

	// take in a response from broker, publish to websocket
	@StreamListener(HelloProducerChannels.RESPONSE)
	public void relayResponse(String resp) {
		webSocket.convertAndSend("/topic/" + HelloProducerChannels.RESPONSE, new HelloResponse(resp));
		System.out.println("response sent: " + resp);
	}
}

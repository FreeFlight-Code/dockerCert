package com.stgconsulting.microdocker;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import com.stgconsulting.microdocker.domain.HelloCount;
import com.stgconsulting.microdocker.domain.HelloMessage;
import com.stgconsulting.microdocker.domain.HelloResponse;
import com.stgconsulting.microdocker.repository.HelloCountRepository;

@Controller
@EnableBinding(HelloCounterChannels.class)
public class HelloCounterController {

	private HelloCounterChannels channels;

	private HelloCountRepository helloCountRepository;

	@Autowired
	public HelloCounterController(HelloCounterChannels channels, HelloCountRepository helloCountRepository) {
		this.channels = channels;
		this.helloCountRepository = helloCountRepository;
	}

	// take in message from broker, enhance with a count, publish back to broker on different topic
	@StreamListener(HelloCounterChannels.HELLO)
	@Transactional
	public void countAndSend(HelloMessage request) {

		System.out.println("Counter received request: " + request);
		HelloCount count = helloCountRepository.findById("hello").orElseGet(() -> {
			System.out.println("Creating new HelloCount");
			HelloCount c = new HelloCount();
			c.setName("hello");
			c.setCount(0L);
			return c;
		});

		long newCount = count.getCount().longValue() + 1;

		count.setCount(newCount);

		helloCountRepository.save(count);

		HelloResponse response = new HelloResponse(request, newCount);

		channels.counted().send(MessageBuilder.withPayload(response).build());

		System.out.println("Message sent: " + response.getName());
	}
}

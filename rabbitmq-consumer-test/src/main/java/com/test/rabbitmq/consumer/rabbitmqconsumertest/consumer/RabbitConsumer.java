package com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer.entity.Employee;

@Service
public class RabbitConsumer {
	
	private ObjectMapper object = new ObjectMapper();

	@RabbitListener(queues = "test.queue")
	public void receiveMessage(String message) {
		System.out.println("Receiver********** " + message);
	}
	
	//Creates 3 queues for the same queue name to consume with concurrency
	@RabbitListener(queues="queue.fixedrate",concurrency = "3")
	public void receiveFixedRate(String message) {
		System.out.println("Consumer**** " + message);
	}
	
	@RabbitListener(queues="json.queue")
	public void receiverJsonMessage(String message) {
		System.out.println("Started Receiving******* " + message);
		try {
			object.readValue(message, Employee.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

package com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer.entity.Employee;

@Service
public class MarktConsumerFanout {
	
	private ObjectMapper object = new ObjectMapper();

	@RabbitListener(queues="q.hr.markt")
	public void receiver(String message) {
		
		System.out.println("Markiting*** " + message);
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

package com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer.entity.Employee;

@Service
public class AccountConsumerFanout {
	
	private ObjectMapper object = new ObjectMapper();

	@RabbitListener(queues="q.hr.account")
	public void receiver(String emp) {
		System.out.println("AccountMarkt*** " + emp);
		try {
			object.readValue(emp, Employee.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

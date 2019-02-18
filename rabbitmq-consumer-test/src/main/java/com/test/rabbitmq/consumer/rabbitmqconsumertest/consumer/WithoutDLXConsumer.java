package com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer;

import java.io.IOException;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP.Channel;
import com.test.rabbitmq.consumer.rabbitmqconsumertest.consumer.entity.Employee;

@Service
public class WithoutDLXConsumer {

	private ObjectMapper object = new ObjectMapper();

//	@RabbitListener(queues="q.no.dlx")
//	public void receiver(String emp) {
//		System.out.println("No DLXXXX*** " + emp);
//		try {
//			Employee empTest = object.readValue(emp, Employee.class);
//			if(empTest.getType().equalsIgnoreCase("typee")) {
//				throw new AmqpRejectAndDontRequeueException("No Reque");
//			}else {
//				System.out.println("No DLXXXX success*** " + emp);
//			}
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	//Solution 2 for DLX, manual acknowledge(Not preferred,bcz if u fail to acknowledge success queue it will be again processed)
	@RabbitListener(queues="q.no.dlx")
	public void receiver(Message message,com.rabbitmq.client.Channel channel) {
		System.out.println("No DLXXXX*** " + message);
		try {
			Employee empTest = object.readValue(message.getBody(), Employee.class);
			if(empTest.getType().equalsIgnoreCase("typee")) {
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
			}else {
				System.out.println("No DLXXXX success*** " + message);
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

/** 
 * Project Name:rabbitmq 
 * File Name:Recv.java 
 * Package Name:com.liu.rabbitmq.simple 
 * Date:2019年1月8日下午5:05:48 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.liu.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.liu.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * ClassName:Recv <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 轮询分发（roud-robin） <br/>
 * Date: 2019年1月8日 下午5:05:48 <br/>
 * 
 * @author liu-guofei
 * @version
 * @since JDK 1.8
 * @see 
 * 
 */
public class Recv {

	private final static String QUEUE_NAME = "test_simple_queue";

	public static void main(String[] args) throws IOException, TimeoutException {

		Connection connection = ConnectionUtils.getConnection();

		// 获取通道
		Channel channel = connection.createChannel();

		// 定义队列的消费者
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
		//以前版本
		/*
		 * DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
		 * 
		 * @Override public void handleDelivery(String consumerTag, Envelope envelope,
		 * BasicProperties properties, byte[] body) throws IOException {
		 * 
		 * super.handleDelivery(consumerTag, envelope, properties, body); String msg =
		 * new String(body,"UTF-8"); System.out.println(msg); } }; //监听队列
		 * channel.basicConsume(QUEUE_NAME, defaultConsumer);
		 */
		// 最新版本
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});

	}
}

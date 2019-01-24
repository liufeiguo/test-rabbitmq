/** 
 * Project Name:rabbitmq 
 * File Name:Send.java 
 * Package Name:com.liu.rabbitmq 
 * Date:2019年1月8日下午3:46:44 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/** 
 * ClassName:Send <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月8日 下午3:46:44 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Send {
	private  final  static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) {
		
		ConnectionFactory factory =new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection;
		try {
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			String message = "Hello Word!";
			
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			
			System.out.println(" [x] Sent '" + message + "'");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		
	}

}
 
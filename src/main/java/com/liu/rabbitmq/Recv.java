/** 
 * Project Name:rabbitmq 
 * File Name:Recv.java 
 * Package Name:com.liu.rabbitmq 
 * Date:2019年1月8日下午3:58:09 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/** 
 * ClassName:Recv <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月8日 下午3:58:09 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Recv {

	private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	    };
	    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	    
	    

   }
}
 
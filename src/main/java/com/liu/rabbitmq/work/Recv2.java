/** 
 * Project Name:rabbitmq 
 * File Name:Recv1.java 
 * Package Name:com.liu.rabbitmq.work 
 * Date:2019年1月9日上午11:13:35 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.liu.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/** 
 * ClassName:Recv1 <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月9日 上午11:13:35 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Recv2 {

	private  final  static String QUEUE_NAME = "test_work_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
	     Connection connection = ConnectionUtils.getConnection();
	     
	     Channel channel = connection.createChannel();
	     
	     channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	     
	     Consumer consumer = new DefaultConsumer(channel) {
	    	 //触发方法
	    	 @Override
	    	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
	    			throws IOException {
	    		
	    		super.handleDelivery(consumerTag, envelope, properties, body);
	    		
	    		String msg = new String(body,"UTF-8");
	    		
	    		System.out.println("[2] Recv msg:"+ msg);
	    		
	    		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}finally {
					System.out.println("[2] done");
				}
	    		
	    	}
	     };
	     boolean autoAck =true;
	     channel.basicConsume(QUEUE_NAME, autoAck, consumer);
	}
}
 
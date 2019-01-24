/** 
 * Project Name:rabbitmq 
 * File Name:Send.java 
 * Package Name:com.liu.rabbitmq.work 
 * Date:2019年1月9日上午11:07:03 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.liu.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * ClassName:Send <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:  (round-robin) 轮询分发 并不会少分发. <br/> 
 * Date:     2019年1月9日 上午11:07:03 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Send {
        
	private  final  static String QUEUE_NAME = "test_work_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		
		
	
	     Connection connection = ConnectionUtils.getConnection();
	     
	     Channel channel = connection.createChannel();
	     
	     channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	     
	     for (int i = 0; i < 50; i++) {
			
	    	 String msg = "hello" +i;
	    	 
	    	 channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
	    	 System.out.println("[MQ] send" + i);
	    	 
	    	 Thread.sleep(i * 20);
		}
	 	channel.close();
		connection.close();
	     
     }
}
 
/** 
 * Project Name:rabbitmq 
 * File Name:Send.java 
 * Package Name:com.liu.rabbitmq.work 
 * Date:2019年1月9日上午11:07:03 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq.workfair;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.liu.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * ClassName:Send <br/> 
 * Function: 公平分发 <br/> 
 * Reason:  (fair dipath) 公平分发 能者多劳<br/> 
 * Date:     2019年1月9日 上午11:07:03 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Send {
    /*
     * 声明好的队列不可修改为持久化    
     */
	private  final  static String QUEUE_NAME = "test_work_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		
		
	
	     Connection connection = ConnectionUtils.getConnection();
	     
	     Channel channel = connection.createChannel();
	     
	     channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	     /*
	               *    每个消费者确认消息前，消息队列不发送到下一个消息到消费者，一次只处理一个消息
	               *   限制发送给同一个消费者，一次最多一条
	      */
	     int prefetchCount = 1;
	     
		channel.basicQos(prefetchCount);
	     
	     for (int i = 0; i < 50; i++) {
			
	    	 String msg = "hello" +i;
	    	 
	    	 channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
	    	 System.out.println("[MQ] send" + i);
	    	 
	    	 Thread.sleep(i * 5);
		}
	 	channel.close();
		connection.close();
	     
     }
}
 
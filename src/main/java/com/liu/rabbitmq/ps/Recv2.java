/** 
 * Project Name:rabbitmq 
 * File Name:Send.java 
 * Package Name:com.liu.rabbitmq.ps 
 * Date:2019年1月18日下午3:08:17 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq.ps;

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
 * ClassName:Send <br/> 
 * Function: 发布订阅模式 <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月18日 下午3:08:17 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class Recv2 {
	
	private  final  static String QUEUE_NAME = "test_queue_fanout_sms";
	
	private  final  static String EXECHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection connection = ConnectionUtils.getConnection();
		
		Channel channel =connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
	
		//声明交换机
		channel.queueBind(QUEUE_NAME,EXECHANGE_NAME ,"");//绑定
		
		channel.basicQos(1);
	     
	     Consumer consumer = new DefaultConsumer(channel) {
	    	 //触发方法
	    	 @Override
	    	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
	    			throws IOException {
	    		
	    		super.handleDelivery(consumerTag, envelope, properties, body);
	    		
	    		String msg = new String(body,"UTF-8");
	    		
	    		System.out.println("[2] Recv msg:"+ msg);
	    		
	    		try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}finally {
					System.out.println("[2] done");
					
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
	    		
	    	}
	     };
	     boolean autoAck = false;//自动应答改成false
	     channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		
	}
}
 
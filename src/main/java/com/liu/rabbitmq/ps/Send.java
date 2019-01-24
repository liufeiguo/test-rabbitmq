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
public class Send {
	
	private  final  static String EXECHANGE_NAME = "test_exchange_fanout";

	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection connection = ConnectionUtils.getConnection();
		
		Channel channel =connection.createChannel();
		/*
		 * fanout(不处理路由键)
		 * direct 处理路由键
		 */
		//声明交换机
		channel.exchangeDeclare(EXECHANGE_NAME, "fanout");//分发
		
		String  msg = "hello ps";
		
		channel.basicPublish(EXECHANGE_NAME, "", null, msg.getBytes());
		
		System.out.println("Send" +msg);
		
		channel.close();
		connection.close();
		
	}
}
 
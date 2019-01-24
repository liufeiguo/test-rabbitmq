/** 
 * Project Name:rabbitmq 
 * File Name:ConnectionUtils.java 
 * Package Name:com.liu.rabbitmq.util 
 * Date:2019年1月8日下午4:05:54 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/** 
 * ClassName:ConnectionUtils <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月8日 下午4:05:54 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class ConnectionUtils {

	/** 
	 * getConnection:(这里用一句话描述这个方法的作用). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx 
	 * @return 
	 * @throws TimeoutException 
	 * @throws IOException 
	 * @since JDK 1.8 
	 */  
	public static Connection getConnection() throws IOException, TimeoutException {
		//工厂
		ConnectionFactory factory = new ConnectionFactory();
		//服务地址
		factory.setHost("127.0.0.1");
		//AMQP 5671
		factory.setPort(5672);
		//设置数据库
		factory.setVirtualHost("/liu");
		
		//用户名
		factory.setUsername("liu");
		
		//密码
		factory.setPassword("123456");
		
	    return factory.newConnection();
		
		
		
	}
	
}
 
package com.jms.message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息队列消费者
 *
 * @description 
 *
 * @author lhf
 * @createDate 2018年5月31日
 */
public class AppConsumer {
	   private static final String QUEUE_NAME = "霜花似雪.Queue"; // 要和Sender一致  
	   
	   public static void main(String[] args) {  
	       ConnectionFactory connectionFactory = null;  
	       Connection connection = null;  
	       Session session = null;  
	       Destination destination = null;  
	       // MessageConsumer: 信息消费者  
	       MessageConsumer consumer = null;  
	       try {  
	           connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,  
	                   ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);  
	           connection = connectionFactory.createConnection();  
	           connection.start();  
	           session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
	           destination = session.createQueue(QUEUE_NAME);  
	           consumer = session.createConsumer(destination);  
	           // 不断地接收信息，直到没有为止  
	           while (true) {  
	               TextMessage message = (TextMessage) consumer.receive();  
	               if (null != message) {  
	                   System.out.print(MessageUtil.cal(message.getText()));  
	               } else {  
	                   break;  
	               }  
	           }  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       } finally {  
	           try {  
	               if (null != connection) {  
	                   connection.close();  
	               }  
	           } catch (Exception e) {  
	               e.printStackTrace();  
	           }  
	       }  
	   }  

}

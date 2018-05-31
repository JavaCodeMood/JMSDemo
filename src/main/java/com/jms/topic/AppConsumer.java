package com.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 主题模式-消息消费者
 *
 * @description 
 *
 * @author lhf
 * @createDate 2018年3月21日
 */
public class AppConsumer {
private static final String url = "tcp://192.168.8.19:61616";   //本地IP地址+61616端口
	
	private static final String topicName = "topic-test";   //队列名字
	
	public static void main(String[] args) throws JMSException {
		//1.创建ConnectionFactory连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		
		//2.创建连接
		Connection connection = connectionFactory.createConnection();
		
		//3.启动连接
		connection.start();
		
		//4.创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//5.创建一个目标
		Destination destination = session.createTopic(topicName);
		
		//6.创建一个消费者
		MessageConsumer consumer = session.createConsumer(destination);
		
		//7.创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				
				try {
					System.out.println("接收消息：" + textMessage.getText());
				} catch (JMSException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		//8.关闭连接
		//connection.close();
	}

}

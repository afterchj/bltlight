package com.tpadsz.after.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class ConsumerService {
    @Resource
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        try{
            System.out.println("从队列" + destination.toString() + "收到了消息：\t" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }

    public TextMessage receiveByName(String destinationName){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destinationName);
        try{
           if (textMessage!=null){
               System.out.println("从队列" +destinationName + "收到了消息：\t" + textMessage.getText());
           }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }
}

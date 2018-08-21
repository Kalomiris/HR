package com.afse.service.queue;

import com.afse.persistence.entity.EmailMassage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;
import java.io.Serializable;


@Stateless
public class MailQueueService extends AbstractQueueService implements Serializable {

    private static final long serialVersionUID = 4955436749160910288L;
    private final static Logger LOGGER = LoggerFactory.getLogger(MailQueueService.class);
    public static final String CONNECTION_FACTORY_LOOKUP_NAME = "java:jboss/DefaultJMSConnectionFactory";
    public static final String ENGINE_QUEUE_NAME = "mail/mailApp";

    private ConnectionFactory connectionFactory;
    private Queue queue;

    @Override
    public void init() {
        this.connectionFactory = (ConnectionFactory) doLookup(CONNECTION_FACTORY_LOOKUP_NAME);
        this.queue = (Queue) doLookup(ENGINE_QUEUE_NAME);
    }


    public void send(EmailMassage message) {
        try {
            send(message, Message.DEFAULT_PRIORITY);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public Queue getQueue() {
        return queue;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }


}

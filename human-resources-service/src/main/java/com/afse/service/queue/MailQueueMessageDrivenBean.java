package com.afse.service.queue;


import com.afse.persistence.dao.EmailDao;
import com.afse.persistence.entity.EmailMassage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(name = "MyEngineQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = MailQueueService.ENGINE_QUEUE_NAME),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MailQueueMessageDrivenBean implements MessageListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailQueueMessageDrivenBean.class);

    @EJB
    private EmailDao emailDao;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                EmailMassage actual = ((EmailMassage) ((ObjectMessage) message).getObject());
                emailDao.save(actual);
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

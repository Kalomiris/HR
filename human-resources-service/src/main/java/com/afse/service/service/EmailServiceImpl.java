package com.afse.service.service;

import com.afse.persistence.entity.EmailMassage;
import com.afse.service.queue.MailQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


/**
 * Stateless Session bean, Service for email
 */
@Stateless
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @EJB
    private MailQueueService mailQueueService;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendMail(String input) {
        EmailMassage emailMassage = new EmailMassage();
        String message = null;
        try {
            switch (input) {
                case "save":
                    message = "Your details are saved successfully!";
                    break;
                case "update":
                    message = "Your details are updated successfully!";
                    break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        emailMassage.setMessage(message);
        mailQueueService.send(emailMassage);
    }
}

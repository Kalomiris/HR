package com.afse.service.queue;

import exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;


public abstract class AbstractQueueService<T extends Serializable> {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractQueueService.class);

    /**
     * Lookup to initialize the {@link ConnectionFactory}
     * and the {@link Queue}
     */
    public abstract void init();

    /**
     * Provide a connection factory to send JMS messages
     *
     * @return JMS {@link ConnectionFactory}
     */
    protected abstract ConnectionFactory getConnectionFactory();

    /**
     * Provide a queue to be used for sending messages
     *
     * @return JMS {@link Queue}
     */
    protected abstract Queue getQueue();

    @PostConstruct
    public void initialization() {
        init();
    }

    /**
     * @param event    The actual message to be sent to the {@link Queue}
     * @param priority The priority of the message
     * @return true if the message was sent successfully
     * @throws Exception
     */
    public boolean send(T event, int priority) throws Exception {
        Connection connection = null;
        Session session = null;

        if (!isValid()) return false;

        try {
            connection = getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(event);
            message.setJMSPriority(priority);
            session.createProducer(getQueue())
                    .send(message);
            return true;
        } catch (Exception e) {
            throw new InvalidInputException(e);
        } finally {
            releaseConnection(connection, session);
        }
    }

    /**
     * Validates the configuration of the service
     *
     * @return true if everything is valid to proceed
     */
    private boolean isValid() {
        if (getConnectionFactory() == null) {
            LOGGER.error("No connection factory was initialized to send message!");
            return false;
        }
        if (getQueue() == null) {
            LOGGER.error("No queue was initialized to send message!");
            return false;
        }
        return true;
    }

    /**
     * Release the connection and the session of the JMS
     *
     * @param connection
     * @param session
     */
    private void releaseConnection(Connection connection, Session session) throws Exception {
        try {
            if (session != null) session.close();
        } catch (JMSException e) {
            throw new Exception(e);
        }
        try {
            if (connection != null) connection.close();
        } catch (JMSException e) {
            throw new InvalidInputException(e);
        }
    }

    /**
     * @param lookupName the name of the bean
     * @param <T>        The type if the bounded object
     * @return The object bound to <tt>name</tt>
     */
    protected <T> T doLookup(String lookupName) {
        try {
            return InitialContext.doLookup(lookupName);
        } catch (NamingException e) {
            LOGGER.error("Cannot find queue name <" + lookupName + ">", e);
            return null;
        }
    }
}

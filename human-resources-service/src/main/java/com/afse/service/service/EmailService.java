package com.afse.service.service;

import java.io.Serializable;

public interface EmailService extends Serializable {

    void sendMail(String input);
}

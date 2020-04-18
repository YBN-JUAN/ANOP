package edu.fzu.anop.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {
    void SendRemindMails() throws IOException, TemplateException, MessagingException;
}

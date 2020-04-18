package edu.fzu.anop.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {
    void sendRemindMails() throws IOException, TemplateException, MessagingException;

    void sendHtmlMail(String to, String subject, String content) throws MessagingException;
}

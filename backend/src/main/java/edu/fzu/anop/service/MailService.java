package edu.fzu.anop.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author ZYF
 */
public interface MailService {
    /**
     * 发送提醒邮件
     *
     * @throws IOException
     * @throws TemplateException
     * @throws MessagingException
     */
    void sendRemindMails() throws IOException, TemplateException, MessagingException;

    /**
     * 发送html模板邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @throws MessagingException
     */
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;
}
